require './lib/gts/gts.rb'
require './lib/gts/helper.rb'
require 'date'
require 'test/unit'

class TestPeriodHelper < Test::Unit::TestCase

  def test_nil_constructor
    helper = GTS::PeriodHelper.new
    assert_not_nil(helper)
    assert_equal(helper.periodicity, GTS::MINUTE)
    assert_not_nil(helper.date_time)
  end

  def test_validate_periodicity
    helper = GTS::PeriodHelper.new
    assert_equal(helper.validate_periodicity(GTS::YEAR), GTS::YEAR)
    assert_equal(helper.validate_periodicity(GTS::MONTH), GTS::MONTH)
    assert_equal(helper.validate_periodicity(GTS::DAY), GTS::DAY)
    assert_equal(helper.validate_periodicity(GTS::HOUR), GTS::HOUR)
    assert_equal(helper.validate_periodicity(GTS::MINUTE), GTS::MINUTE)
    assert_equal(helper.validate_periodicity(GTS::SECOND), GTS::SECOND)

    # Test bogus values all default to GTS::MINUTE
    assert_equal(helper.validate_periodicity(nil), GTS::MINUTE)
    assert_equal(helper.validate_periodicity('bubba_fet'), GTS::MINUTE)
    assert_equal(helper.validate_periodicity(-1), GTS::MINUTE)
    assert_equal(helper.validate_periodicity(-99999999), GTS::MINUTE)
    assert_equal(helper.validate_periodicity(99999999), GTS::MINUTE)
  end

  def test_year_periodicity
    helper = GTS::PeriodHelper.new("14 Mar 2015 00:01:02 GMT/1")
    assert_equal(helper.periodicity, GTS::YEAR)
    assert_equal(helper.date_time.year, 2015)
    assert_equal(helper.date_time.month, 3)
    assert_equal(helper.date_time.day, 14)
    assert_equal(helper.date_time.hour, 0)
    assert_equal(helper.date_time.min, 1)
    assert_equal(helper.date_time.sec, 2)
  end
end
