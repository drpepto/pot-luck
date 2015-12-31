require './lib/gts/block_hash.rb'
require 'date'
require 'test/unit'

class TestBlockHash < Test::Unit::TestCase
  def test_constructor
    bh = GTS::BlockHash.new

    # The hash of an empty string
#    assert_equal(bh.hash, 'e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855')
  end
end
