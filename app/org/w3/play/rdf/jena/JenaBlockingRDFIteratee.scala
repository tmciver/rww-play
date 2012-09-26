package org.w3.play.rdf.jena

import org.w3.banana.jena.{Jena, JenaGraphSyntax, JenaOperations}
import org.w3.banana.{Turtle, RDFXML, RDFReader}
import org.w3.play.rdf.{IterateeSelector, RDFIteratee, BlockingRDFIteratee}

/**
 *
 * Date: 27/06/2012
 */

object JenaBlockingRDFIteratee  {
  implicit val ops = JenaOperations
  import org.w3.banana.jena.Jena.{turtleReader,rdfxmlReader}

  def apply[SyntaxType](implicit jenaSyntax: JenaGraphSyntax[SyntaxType],
                        reader: RDFReader[Jena, SyntaxType]) = new BlockingRDFIteratee[Jena,SyntaxType]

  implicit val RDFXMLIteratee: RDFIteratee[Jena#Graph,RDFXML] = JenaBlockingRDFIteratee[RDFXML]
  implicit val TurtleIteratee: RDFIteratee[Jena#Graph,Turtle] = JenaBlockingRDFIteratee[Turtle]

  val rdfxmlSelector = IterateeSelector[Jena#Graph, RDFXML]
  val turtleSelector = IterateeSelector[Jena#Graph, Turtle]

  implicit val BlockingIterateeSelector: IterateeSelector[Jena#Graph] =
    rdfxmlSelector combineWith turtleSelector


}