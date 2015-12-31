require 'date'
require './lib/gts/gts.rb'

class GTS::BST
  attr_accessor :key, :value, :left, :right
  
  def initialize(key=nil, value=nil)
    @key = key
    @value = value
  end
  
  def insert(key, value)
    if(@key.nil?)
      @key = key
      @value = value
      return
    end
  
    if @key < key
      if @right.nil?
        @right = GTS::BST.new(key, value)
      else
        @right.insert(key, value)
      end
    else
      if @left.nil?
        @left = GTS::BST.new(key, value)
      else
        @left.insert(key, value)
      end
    end
  end
  
  def search(key)
    if(@key.nil? || key.nil?)
      return nil
    end

    if(@key == key)
      return @value
    end

    begin
      if(@key < key)
        return @right.search(key)
      else
        return @left.search(key)
      end
    rescue 
      return nil
    end
  end

  def each(&blk)
    BST.traverse_preorder(&blk)
  end

  def self.traverse_preorder(node, &blk)
    node.left.traverse_preorder(&blk)
    blk.call(node.key)
    node.right.traverse_preorder(&blk)
  end
end
