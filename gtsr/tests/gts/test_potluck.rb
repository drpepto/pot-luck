require './lib/gts/potluck.rb'
require 'test/unit'

# A PotLuck represents a Period of time in which people may submit
# random dishes of data to the PotLuck. When the Period expires, all
# the dishes in the PotLuck are hashed together to produce a final
# PotLuck id.
class TestPotLuck < Test::Unit::TestCase

  # Objects may be instantiated in a nil state prior to rehydration
  def test_nil_constructor
    pl = GTS::PotLuck.new
    assert_nil(pl.namespace)
    assert_nil(pl.period)
    assert_nil(pl.name)
    assert_not_nil(pl.dishes)
  end

  # But trying to add a dish to a nil Pot Luck throws and exception
  def test_nil_constructor_to_string_throws_exception
    pl = GTS::PotLuck.new
    assert_raise GTS::MissingNameSpace do
      assert_not_nil(pl.add_dish(nil))
    end
  end

  # Test Constructor Happy Path
  def test_constructor_happy_path
    pl = GTS::PotLuck.new('os3.cc', '01 Jan 2016 00:00:00 UTC/1', 'my-potluck')
    assert_equal('os3.cc', pl.namespace)
    assert_equal('01 Jan 2016 00:00:00 UTC/1', pl.period)
    assert_equal('my-potluck', pl.name)
    assert_equal(0, pl.dishes.size)
  end

  # Test Adding Dishes
  def test_adding_dishes_by_list_of_strings
    pl = GTS::PotLuck.new('os3.cc', '01 Jan 2016 00:00:00 UTC/1', 'my-potluck')
    dishes = [
      '{"submitter":"Andy Olsen","period":"01 Jan 2016 00:00:00 UST/1","random":"Now is the time for all great men..."}',
      '{"submitter":"Cecil Merryweather","period":"01 Jan 2016 00:00:00 UST/1","random":"Cecil Rides again!"}',
      '{"submitter":"Horatio Bulger","period":"01 Jan 2016 00:00:00 UST/1","random":"Dr. Livingston I presume?"}',
    ]
    pl.add_dishes(dishes)
    assert_equal(3, pl.dishes.size)
  end

end
