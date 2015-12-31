module GTS
  YEAR    = 1
  MONTH   = 2
  DAY     = 3
  HOUR    = 4
  MINUTE  = 5
  SECOND  = 6
  GLOBAL_DATE_FORMAT = '%d %b %Y %H:%M:%S %Z'
  class MissingNameSpace < StandardError; end
  class MissingPeriod < StandardError; end
  class MissingName < StandardError; end
end
