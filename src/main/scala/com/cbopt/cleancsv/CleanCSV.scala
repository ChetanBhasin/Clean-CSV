package com.cbopt.cleancsv

/**
 * Created by chetan on 17/06/15.
 */

trait CSVParser

object CleanCSV {
}

/**
 * A line by line parser for CSV
 * @param mode
 */
class CsvLineParser(mode: ParseMode = StringParse) extends CSVParser {

  /**
   * Scala generator method for direcly using the parser object
   * @param input Input String
   * @return Iterable with type depending upon what is the mode
   */
  def apply(input: String) = mode match {
    case StringParse => parseString(input)
    case TypeParse => parseType(input)
    case AnyParse => parseAny(input)
  }

  /**
   * Parse directly and return standard Scala values in an Any array
   * @param input Input String
   * @return Array[Any]
   */
  def parseAny(input: String) = this.parseType(input) map {
    _ match {
      case CleanCsvInt(string) => string
      case CleanCsvInt(integer) => integer
      case CleanCsvDouble(double) => double
    }
  }

  /**
   * Parse directly and return CleanCsvType values
   * @param input Input String
   * @return Array[CleanCsvType]
   */
  def parseType(input: String) = {
    this.parseString(input) map { element => Extractors.getIntValue(element) match {
      case x: Int => CleanCsvInt(x)
      case _ => Extractors.getDoubleValue(element) match {
        case x: Double => CleanCsvDouble(x)
        case _ => CleanCsvString(element)
      }
    }
    }
  }

  /**
   * Parse directly and return strings
   * @param input Input String
   * @return Array[String]
   */
  def parseString(input: String): Array[String] = input.split(Extractors.expression, -1)

}

/**
 * A customizable CSV file parser class
 * @param file Filepath
 * @param head Weather there is a heading in the CSV file
 * @param mode Parsing mode defined in ParseMode
 * @param async Weather to use sync or async parsing
 */
class CsvFileParse(file: String, head: Boolean, mode: ParseMode = StringParse, async: Boolean = false) {

  lazy val filestream = scala.io.Source.fromFile(file)
  lazy val parse = {
    val lines = filestream.getLines.toList

    if (async) {
      /**
       * Async CSV file parser
       * @param input Input lines
       * @return Parsed collection
       */
      def parse(input: List[String]) = input.par.map(lineParser(_))

      if (head) (lineParser(lines.head), parse(lines.tail))
      else parse(lines)
    } else {
      /**
       * Sync CSV file parser
       * @param input Input lines
       * @return Parsed collection
       */
      def parse(input: List[String]) = input.map(lineParser(_))
      if (head) (lineParser(lines.head), parse(lines.tail))
      else parse(lines.tail)
    }

  }
  private lazy val lineParser = new CsvLineParser(mode)

}

/**
 * A set of private utitlities for use
 */
private[cleancsv] object Extractors {

  private[cleancsv] val expression = "\",(?=([^\\\"]*\\\"[^\\\"]*\\\")*[^\\\"]*$)\""

  def getIntValue(s: String): Int = s match {
    case _ => s.toInt
  }

  def getDoubleValue(s: String): Double = s match {
    case _ => s.toDouble
  }

}