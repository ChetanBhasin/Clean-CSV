package com.cbopt.cleancsv

/**
 * Created by chetan on 17/06/15.
 */

/**
 * Marker trait to define the type of outputs
 */
trait CleanCsvType {
  def toString: String
}

/**
 * Integer type for CSV parsing
 * @param toInt Integer value
 */
case class CleanCsvInt(toInt: Int) extends CleanCsvType {
  override def toString = toInt.toString
}

/**
 * Double type for CSV parsing
 * @param toDouble Double value
 */
case class CleanCsvDouble(toDouble: Double) extends CleanCsvType {
  override def toString = toDouble.toString
}

/**
 * Default string type for CSV parsing
 * @param s String value
 */
case class CleanCsvString(s: String) extends CleanCsvType {
  override def toString = s
}

/**
 * Marker trait for parsing modes
 */
trait ParseMode

/**
 * A set of all the parsing modes available
 */
case object AnyParse extends ParseMode

// Parsing will return an array of all possible types
case object StringParse extends ParseMode

// Parsing will return an array of strings
case object TypeParse extends ParseMode

// Parsing will return an array of defined types

/**
 * Marker trait for parsing methods available
 */
trait ParseMethod

/**
 * A set of all the parsing methods available so far
 */
case object PERMISSIVE extends ParseMethod

case object FAILFAST extends ParseMethod

case object DROPMALFORM extends ParseMethod
