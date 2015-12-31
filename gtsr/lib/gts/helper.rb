require 'date'
require './lib/gts/gts.rb'

module GTS
  class PeriodHelper
    # Accept a period string and parse out the date time element and
    # the periodicity element and then validate them both
    def initialize(period=nil)
      if(period.nil?)
        @periodicity = GTS::MINUTE
        @date_time = Time.new.utc
      else 
        temp = period.split('/')
        raise "invalid format" if temp.length != 2
        @periodicity = validate_periodicity(temp[1])
        @date_time = DateTime.strptime(temp[0], GTS::GLOBAL_DATE_FORMAT).to_time.utc
      end
    end

    # Either value will match a period, or default to GTS::MINUTE
    def validate_periodicity(value=nil?)
      periodicity = GTS::MINUTE
      if(!value.nil?)
        num = value.to_i
        case num
        when GTS::YEAR
          periodicity = GTS::YEAR
        when GTS::MONTH
          periodicity = GTS::MONTH
        when GTS::DAY
          periodicity = GTS::DAY
        when GTS::HOUR
          periodicity = GTS::HOUR
        when GTS::MINUTE
          periodicity = GTS::MINUTE
        when GTS::SECOND
          periodicity = GTS::SECOND
        end
      end
      return periodicity
    end

    def periodicity
      return @periodicity
    end

    def date_time
      return @date_time
    end
  end
end
