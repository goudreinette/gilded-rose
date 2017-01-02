(ns products)

; Product types: Product, Legendary, Conjured, Ripening, Ticket



(defn product [name type & args]
  (merge {:name name
          :type (or type :default)
          :quality 50}
         (select-keys (apply hash-map args)
                      [:type :quality :sell-in])))

(defn within-bounds [n]
  (cond
    (<= n 0)  0
    (>= n 50) 50
    :else     n))

(defn age-normally [{:keys [sell-in quality]} & [multiplier]]
  (if (< 0 sell-in)
    (- quality (* 2 (or multiplier 1)))
    (- quality (* 1 (or multiplier 1)))))


(defmulti update-quality :type)

(defmethod update-quality :default [this]
  (within-bounds (age-normally this)))

(defmethod update-quality :conjured [this]
  (within-bounds (age-normally this 2)))

(defmethod update-quality :ripening [{:keys [quality]}]
  (within-bounds (+ quality 2)))

(defmethod update-quality :legendary [{:keys [quality]}]
  quality)

(defmethod update-quality :ticket [{:keys [sell-in quality]}]
  (within-bounds
    (condp #(<= %2 %1) sell-in
      0  0
      5  (+ quality 3)
      10 (+ quality 2)
         (+ quality 1))))
