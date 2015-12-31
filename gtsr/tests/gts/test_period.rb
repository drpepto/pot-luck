require './lib/gts/period.rb'
require 'date'
require 'test/unit'

class TestPeriod < Test::Unit::TestCase

  def test_to_string
    period = GTS::Period.new("29 Feb 2000 13:14:15 UTC/1")
    assert_equal(period.to_s, "29 Feb 2000 13:14:15 UTC/1")
  end

  def test_yearly_periodicity
    period = GTS::Period.new("29 Feb 2000 13:14:15 UTC/1")
    assert_equal(period.periodicity, GTS::YEAR)
    assert_equal(period.start_date.year, 2000)
    assert_equal(period.start_date.month, 1)
    assert_equal(period.start_date.day, 1)
    assert_equal(period.start_date.hour, 0)
    assert_equal(period.start_date.min, 0)
    assert_equal(period.start_date.sec, 0)

    assert_equal(period.end_date.year, 2000)
    assert_equal(period.end_date.month, 12)
    assert_equal(period.end_date.day, 31)
    assert_equal(period.end_date.hour, 23)
    assert_equal(period.end_date.min, 59)
    assert_equal(period.end_date.sec, 59)
  end

  def test_monthly_periodicity
    period = GTS::Period.new("29 Feb 2000 13:14:15 UTC/2")
    assert_equal(period.periodicity, GTS::MONTH)
    assert_equal(period.start_date.year, 2000)
    assert_equal(period.start_date.month, 2)
    assert_equal(period.start_date.day, 1)
    assert_equal(period.start_date.hour, 0)
    assert_equal(period.start_date.min, 0)
    assert_equal(period.start_date.sec, 0)

    assert_equal(period.end_date.year, 2000)
    assert_equal(period.end_date.month, 2)
    assert_equal(period.end_date.day, 29)
    assert_equal(period.end_date.hour, 23)
    assert_equal(period.end_date.min, 59)
    assert_equal(period.end_date.sec, 59)
  end

  def test_daily_periodicity
    period = GTS::Period.new("29 Feb 2000 13:14:15 UTC/3")
    assert_equal(period.periodicity, GTS::DAY)
    assert_equal(period.start_date.year, 2000)
    assert_equal(period.start_date.month, 2)
    assert_equal(period.start_date.day, 29)
    assert_equal(period.start_date.hour, 0)
    assert_equal(period.start_date.min, 0)
    assert_equal(period.start_date.sec, 0)

    assert_equal(period.end_date.year, 2000)
    assert_equal(period.end_date.month, 2)
    assert_equal(period.end_date.day, 29)
    assert_equal(period.end_date.hour, 23)
    assert_equal(period.end_date.min, 59)
    assert_equal(period.end_date.sec, 59)
  end

  def test_hourly_periodicity
    period = GTS::Period.new("29 Feb 2000 13:14:15 UTC/4")
    assert_equal(period.periodicity, GTS::HOUR)
    assert_equal(period.start_date.year, 2000)
    assert_equal(period.start_date.month, 2)
    assert_equal(period.start_date.day, 29)
    assert_equal(period.start_date.hour, 13)
    assert_equal(period.start_date.min, 0)
    assert_equal(period.start_date.sec, 0)

    assert_equal(period.end_date.year, 2000)
    assert_equal(period.end_date.month, 2)
    assert_equal(period.end_date.day, 29)
    assert_equal(period.end_date.hour, 13)
    assert_equal(period.end_date.min, 59)
    assert_equal(period.end_date.sec, 59)
  end

  def test_minutely_periodicity
    period = GTS::Period.new("29 Feb 2000 13:14:15 UTC/5")
    assert_equal(period.periodicity, GTS::MINUTE)
    assert_equal(period.start_date.year, 2000)
    assert_equal(period.start_date.month, 2)
    assert_equal(period.start_date.day, 29)
    assert_equal(period.start_date.hour, 13)
    assert_equal(period.start_date.min, 14)
    assert_equal(period.start_date.sec, 0)

    assert_equal(period.end_date.year, 2000)
    assert_equal(period.end_date.month, 2)
    assert_equal(period.end_date.day, 29)
    assert_equal(period.end_date.hour, 13)
    assert_equal(period.end_date.min, 14)
    assert_equal(period.end_date.sec, 59)
  end

  def test_secondly_periodicity
    period = GTS::Period.new("29 Feb 2000 13:14:15 UTC/6")
    assert_equal(period.periodicity, GTS::SECOND)
    assert_equal(period.start_date.year, 2000)
    assert_equal(period.start_date.month, 2)
    assert_equal(period.start_date.day, 29)
    assert_equal(period.start_date.hour, 13)
    assert_equal(period.start_date.min, 14)
    assert_equal(period.start_date.sec, 15)

    assert_equal(period.end_date.year, 2000)
    assert_equal(period.end_date.month, 2)
    assert_equal(period.end_date.day, 29)
    assert_equal(period.end_date.hour, 13)
    assert_equal(period.end_date.min, 14)
    assert_equal(period.end_date.sec, 15)
  end

  # Test a plain vanilla constructor returns a period based on now
  # with a periodicity of MINUTE
  def test_nil_constructor
    now = Time.new.utc

    period = GTS::Period.new

    # Ensure the object is not null
    assert_not_nil(period)

    # It should have a default periodicty of MINUTE
    assert_true(period.periodicity == GTS::MINUTE)

    # It should have a start_date that is not nil
    assert_not_nil(period.start_date)

    # The year, month, date and hour should all match now
    assert_equal(period.start_date.year, now.year)
    assert_equal(period.start_date.month, now.month)
    assert_equal(period.start_date.day, now.day)
    assert_equal(period.start_date.hour, now.hour)
    assert_equal(period.start_date.min, now.min)

    # It should have an end date that is not nil
    assert_not_nil(period.end_date)
    assert_equal(period.end_date.year, now.year)
    assert_equal(period.end_date.month, now.month)
    assert_equal(period.end_date.day, now.day)
    assert_equal(period.end_date.hour, now.hour)
    assert_equal(period.end_date.min, now.min)
  end

end
