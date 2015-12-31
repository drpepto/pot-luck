require 'digest'
require './lib/gts/gts.rb'

class GTS::PotLuck
  attr_accessor :namespace, :period, :name, :dishes
  def initialize(namespace=nil, period=nil, name=nil)
    @namespace = namespace
    @period = period
    @name = name
    @dishes = []
    @validated = false
  end

  def add_dishes(list)
    list.each do |d|
      dish = GTS::Dish.new
      dish.parse(d)
      add_dish(dish)
    end
  end

  def add_dish(dish)
    validate()
    @dishes << dish
  end

  def to_s
  end

  def validate()
    return if @validated
    raise GTS::MissingNameSpace if(@namespace.nil?)
    raise GTS::MissingPeriod if(@period.nil?)
    raise GTS::MissingName if(@name.nil?)
    @validated = true
  end
end
