(ns pbt.pbt-test
 (:require [clojure.test.check :as tc]
            [clojure.test.check.generators :as gen]
            [clojure.test.check.properties :as prop]
            [clojure.test.check.clojure-test :refer [defspec]]
            [clojure.string :as str]))

(defn lower-case? [c]
  (and (>= (int c) (int \a))
       (<= (int c) (int \z))))

(def gen-lower-case (gen/such-that lower-case? gen/char-alpha 100))

(defspec lower-case?-works
  100
  (prop/for-all [c gen/char-ascii]
    (= (lower-case? c)
       (and (>= (int c) (int \a))
            (<= (int c) (int \z))))))

