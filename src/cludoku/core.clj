(ns cludoku.core
  (:gen-class))

(use 'clojure.set)

(defn parse-row
  "turns raw puzzle data into useful data"
    [x]
    (->> (clojure.string/split x #" ")
      (mapv read-string)
      (mapv (fn [x] (if (= x '_) nil x)))))

(defn load-puzzle
  "Reads a sudoku puzzle in a specified format"
  [filename]
  (with-open [rdr (clojure.java.io/reader filename)]
    (->> (line-seq rdr)
      (reduce conj [])
      (mapv parse-row))))

(defn row
  [puzzle y]
  (get puzzle y))

(defn get-sq
  [puzzle x y]
  (get (get puzzle y) x))

(defn col
  [puzzle x]
  (let [coords (for [y (range 0 9)] [x y])]
    (map (fn [x] (apply get-sq (concat [puzzle] x))) coords)))

(defn grid
  "returns a col first 3x3 grid, coords are in (range 0 3)"
  [puzzle _x _y]
  (let [ -x (* 3 (int (/ _x 3)))
         -y (* 3 (int (/ _y 3)))
         coords (for [x (range -x (+ -x 3)) y (range -y (+ -y 3))] [x y])]
    (map (fn [x]
      (apply get-sq (concat [puzzle] x))) coords)))

(defn present-numbers
  [group]
  (set (filter (fn [x] (not (nil? x))) group)))

(def all-numbers
  (set (range 1 10)))

(defn possibles 
  [puzzle x y]
  (if (not (nil? (get-sq puzzle x y)))
    (get-sq puzzle x y)
    (let [ _col (present-numbers (col puzzle x))
           _row (present-numbers (row puzzle y))
           _grid (present-numbers (grid puzzle x y))
           all-present (clojure.set/union _col _row _grid)]
      (clojure.set/difference all-numbers all-present))))

; quick algo idea:
; go through each col/row/grid
; for each, generate a set of possibilities for each square
; by finding whats available via difference from (set range(1 10))
; then, we will have 3 sets of for each unknown square
; the intersection of those sets are the possibilities
; after that, cry if it isn't solved
; EDIT: possibles calculates whatever is in the realm of possibility
; I need to write some unit test of the various functions, I made a few
; mistakes and it would have made my dev process much easier

(def puzzle
  (load-puzzle "puzzle.txt"))

(def kim-possible
  (partial possibles puzzle))

(def all-coords
  (for [x (range 0 9) y (range 0 9)] [x y]))

; Make a better display func
(->> all-coords
  (map (fn [x] (apply kim-possible x)))
  (map println))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (->> all-coords
    (map (fn [x] (apply kim-possible x)))
    (map println)))
