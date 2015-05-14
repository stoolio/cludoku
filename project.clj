(defproject cludoku "0.1.0-SNAPSHOT"
  :description "Solve sudoku puzzles the easy way"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]]
  :main ^:skip-aot cludoku.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
