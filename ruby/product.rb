class BasicProduct
  attr_reader :quality

  def initialize(args)
    args.each do |k, v|
      instance_variable_set("@#{k}", v)
    end
  end
end

module Aging
  attr_reader :sell_in

  def age
    self.class.new name: name, sell_in: sell_in - 1, quality: next_quality
  end
end

class Product < BasicProduct
  include Aging

  attr_reader :name

  def age_rate
    1
  end

  def next_quality
    if quality > 50
      50
    elsif quality <= 1
      0
    elsif sell_in <= 0
      quality - age_rate * 2
    else
      quality - age_rate
    end
  end
end


class Conjured < Product
  def age_rate
    2
  end
end


class AgedBrie < BasicProduct
  include Aging

  def name
    "Aged Brie"
  end

  def next_quality
    if quality >= 49
      50
    else
      quality + 2
    end
  end
end


class Sulfuras < BasicProduct
  def quality
    80
  end

  def age
    self
  end

  def name
    "Sulfuras"
  end
end


class BackstagePasses
  include Aging

  def name
    "Backstage Passes"
  end

  def next_quality
    case sell_in
    when <= 10
      quality + 2
    when <= 5
      quality + 3
    when <= 0
      0
    end
  end
end
