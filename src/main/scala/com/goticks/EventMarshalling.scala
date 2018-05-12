package com.goticks

import spray.json._

trait EventMarshalling extends DefaultJsonProtocol {
  implicit val stringFormat = jsonFormat1(String)

}

