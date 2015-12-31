require './lib/gts/dish.rb'
require 'test/unit'

class TestDish < Test::Unit::TestCase

  # Ensure when we create a dish object without any parameters that we
  # at least get psuedo random data
  def test_nil_constructor
    dish = GTS::Dish.new

    # Submitter and period should default to an empty string if they
    # are nil
    assert_not_nil(dish.submitter)
    assert_equal('', dish.submitter)
    assert_not_nil(dish.period)
    assert_equal('', dish.period)

    # The random value must not be allowed to be nil. If it is, it
    # will default to system generated randomness
    assert_not_nil(dish.random)
  end

  # Ensure we can construct a new object with all parameters, hash it
  # and then convert it to json
  def test_constructor_known_args
    # The algorithm we are testing is Hash(Submitter + Period + Random)
    # We can generate the hash we expect on the unix command line:

    # echo -n 'Andy Olsen01 Jan 2016 00:00:00 UST/1Now is the time for all great men...' | sha256sum
    expected_hash = '433952842a6d452b69134238c5a40dc87ece8c3cbb6c3ec2bfc2048a34aff583'
    dish = GTS::Dish.new('Andy Olsen', '01 Jan 2016 00:00:00 UST/1', 'Now is the time for all great men...')
    assert_equal(expected_hash, dish.hash)

    expected_json = '{"submitter":"Andy Olsen","period":"01 Jan 2016 00:00:00 UST/1","random":"Now is the time for all great men...","hash":"433952842a6d452b69134238c5a40dc87ece8c3cbb6c3ec2bfc2048a34aff583"}'
    assert_equal(expected_json, dish.to_s)
  end

  # Ensure we can parse a json representation of a dish back into
  # object form
  def test_parse_object_from_json
    object_json = '{"submitter":"Andy Olsen","period":"01 Jan 2016 00:00:00 UST/1","random":"Now is the time for all great men...","hash":"433952842a6d452b69134238c5a40dc87ece8c3cbb6c3ec2bfc2048a34aff583"}'
    dish = GTS::Dish.new
    dish.parse(object_json)
    assert_equal(dish.submitter, 'Andy Olsen')
    assert_equal(dish.period, '01 Jan 2016 00:00:00 UST/1')
    assert_equal(dish.random, 'Now is the time for all great men...')
  end

end
