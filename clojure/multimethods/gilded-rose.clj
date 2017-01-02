(ns gilded-rose
  (:require [products :refer [product]]))

(def inventory [(product "+5 Dexterity Vest" :sell-in 10 :quality 20)
                (product "Aged Brie" :type :ripening :sell-in 2 :quality 12)
                (product "Elixir of the Mongoose" :sell-in 5 :quality 7)
                (product "Sulfuras, Hand of Ragnaros" :type :legendary :quality 80)])
