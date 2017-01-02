(ns products)

; Product types: Product, Legendary, Conjured, Ripening, Ticket

(defn within-bounds [n]
  (condp #(%1 %2) n
    (<= 0)  0
    (>= 50) 50))

(defprotocol IsProduct
  (update-quality [this]))

(defrecord Product [name sell-in quality] IsProduct
  (update-quality [{:keys [sell-in quality]}]
    (within-bounds
      (if (< 0 sell-in)
        (- quality 2)
        (- quality 1)))))

(defrecord Ripening [name sell-in quality] IsProduct
  (update-quality [{:quality quality}]
    (within-bounds (+ quality 2))))


(defrecord Legendary [name sell-in quality] IsProduct
  (update-quality [this] 80))

(defrecord Conjured [name sell-in quality] IsProduct
  (update-quality [this]
    (within-bounds
      (if (< 0 sell-in)
        (- quality 4)
        (- quality 2)))))

(defrecord Ticket [name sell-in quality] IsProduct
  (update-quality [{:keys [name sell-in quality]}]
    (within-bounds
      (condp <= sell-in
        10 (+ quality 2)
        5  (+ quality 3)))))
