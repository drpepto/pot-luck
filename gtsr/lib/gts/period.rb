require 'date'
require './lib/gts/gts.rb'
require './lib/gts/helper.rb'

# Subnet a Time in a manner similar to subnetting an ipv4
# address
module GTS
class GTS::Period

  # Constructor
  # Period is a string of the format: "01 Jan 2015 13:11:11 MST/1"
  def initialize(period=nil)
    helper = PeriodHelper.new(period)
    @date_time = helper.date_time
    @periodicity = helper.periodicity

    # Based on the periodicity, set the start and end date ranges
    case @periodicity
    when YEAR
      @start_date = Time.new(@date_time.year, 1, 1, 0, 0, 0)
      @end_date   = Time.new(@date_time.year, 12, 31, 23, 59, 59)
    when MONTH
      @start_date = Time.new(@date_time.year, @date_time.month, 1, 0, 0, 0)
      @end_date   = DateTime.civil(@date_time.year, @date_time.month, -1, 23, 59, 59).to_time.utc
    when DAY
      @start_date = Time.new(@date_time.year, @date_time.month, @date_time.day, 0, 0, 0)
      @end_date   = DateTime.civil(@date_time.year, @date_time.month, @date_time.day, 23, 59, 59).to_time.utc
    when HOUR
      @start_date = Time.new(@date_time.year, @date_time.month, @date_time.day, @date_time.hour, 0, 0)
      @end_date   = DateTime.civil(@date_time.year, @date_time.month, @date_time.day, @date_time.hour, 59, 59).to_time.utc
    when MINUTE
      @start_date = Time.new(@date_time.year, @date_time.month, @date_time.day, @date_time.hour, @date_time.min, 0)
      @end_date   = DateTime.civil(@date_time.year, @date_time.month, @date_time.day, @date_time.hour, @date_time.min, 59).to_time.utc
    when SECOND
      @start_date = @date_time
      @end_date   = @date_time
    end
  end

  def start_date
    return @start_date
  end

  def end_date
    return @end_date
  end

  def periodicity
    return @periodicity
  end

  def to_s
    return @date_time.utc.strftime(GTS::GLOBAL_DATE_FORMAT) + '/' + @periodicity.to_s
  end
end
end

