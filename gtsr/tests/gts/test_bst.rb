require './lib/gts/bst.rb'
require 'date'
require 'test/unit'

class TestBlockHash < Test::Unit::TestCase
  def test_insert
    data = [
            { :key => 1, :value => 'Tokyo' },
            { :key => 5, :value => 'New York' },
            { :key => 10, :value => 'Singapore' },
            { :key => 15, :value => 'Sydney' }
           ]

    bh = GTS::BST.new()
    data.each do |d|
      bh.insert(d[:key], d[:value])
    end
    
    assert(bh.search(1) == 'Tokyo')
    assert(bh.search(5) == 'New York')
    assert(bh.search(10) == 'Singapore')
    assert(bh.search(15) == 'Sydney')
    assert(bh.search(nil) == nil)
    assert(bh.search('Some value not in tree') == nil)
  end
end
