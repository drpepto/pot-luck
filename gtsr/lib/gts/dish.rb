require 'digest'
require 'json'
require './lib/gts/gts.rb'

# A Dish represents randomness someone wishes to add to the Pot
# Luck. It consists of a final SHA256 hash of three items: The name of
# the submitter, the period the dish is to submitted for and a random
# string of bytes.
class GTS::Dish
  attr_accessor :submitter, :period, :random
  
  def initialize(submitter=nil, period=nil, random=nil)
    # This class needs some source of randomness in case the user does
    # not supply it
    @seed = Random.new_seed
    @prng = Random.new(@seed)

    # Submitter can be any string. The idea is to not have two
    # identical random value hash to the same value by using submitter
    # as a namespace. Will default to '' if not supplied
    @submitter = submitter
    @submitter = '' if @submitter.nil?

    # The string representation of a period, will default to '' if not
    # supplied.
    @period = period
    @period = '' if @period.nil?

    # Any random series of data. If not supplied by the user, it will
    # default to Ruby's PRNG
    @random = random
    @random = @prng.bytes(64).unpack('H*') if @random.nil?
  end

  # Parse a json string representing a dish and populate this object
  # with the data overwriting anything already present.
  def parse(json_s)
    hash = JSON.parse(json_s)
    @submitter = hash[:submitter.to_s]
    @period = hash[:period.to_s]
    @random = hash[:random.to_s]
  end

  # Output a JSON representation of this object
  def to_s
    JSON.generate({:submitter => @submitter, :period => @period, :random => @random, :hash => hash()})
  end

  # Generate a hash of this object. It consists of Submitter + Period
  # + Random
  def hash
    @digest = Digest::SHA256.new
    @digest.update @submitter
    @digest.update @period
    @digest.update @random
    return @digest.hexdigest
  end
end
