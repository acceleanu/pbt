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
(defn sign [x]
  (cond
    (zero? x) 0
    (neg? x) -1
    :else 1))

(defspec equivalent-comparison 10000
  (prop/for-all [a gen/string-ascii]
  (>= (.hashCode a) 0 )))

