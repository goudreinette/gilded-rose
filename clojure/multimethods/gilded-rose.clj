(ns gilded-rose
  (:require [products :refer [product update-product]]))

(def inventory [(product "+5 Dexterity Vest" :sell-in 10 :quality 20)
                (product "Aged Brie" :type :ripening :sell-in 2 :quality 12)
                (product "Elixir of the Mongoose" :sell-in 5 :quality 7)
                (product "Sulfuras, Hand of Ragnaros" :type :legendary :quality 80)
                (product "Backstage passes to a TAFKAL80ETC concert" :type :ticket :quality 20 :sell-in 15)
                (product "Conjured Mana Cake" :type :conjured :sell-in 3 :quality 6)])


(defn update-inventory [inventory]
  (map update-product inventory))
