package api

import org.openapitools.OpenApiExceptions
import javax.inject.{Inject, Singleton}
import play.api.libs.json._
import play.api.mvc._
import model.Order

@javax.annotation.Generated(value = Array("org.openapitools.codegen.languages.ScalaPlayFrameworkServerCodegen"), date = "2019-03-26T02:45:22.426+02:00[Asia/Jerusalem]")
@Singleton
class StoreApiController @Inject()(cc: ControllerComponents, api: StoreApi) extends AbstractController(cc) {
  /**
    * DELETE /store/order/:orderId
    * @param orderId ID of the order that needs to be deleted
    */
  def deleteOrder(orderId: String): Action[AnyContent] = Action { request =>
    def executeApi(): Unit = {
      api.deleteOrder(orderId)
    }

    executeApi()
    Ok
  }

  /**
    * GET /store/inventory
    */
  def getInventory(): Action[AnyContent] = Action { request =>
    def executeApi(): Map[String, Int] = {
      api.getInventory()
    }

    val result = executeApi()
    val json = Json.toJson(result)
    Ok(json)
  }

  /**
    * GET /store/order/:orderId
    * @param orderId ID of pet that needs to be fetched
    */
  def getOrderById(orderId: Long): Action[AnyContent] = Action { request =>
    def executeApi(): Order = {
      api.getOrderById(orderId)
    }

    val result = executeApi()
    val json = Json.toJson(result)
    Ok(json)
  }

  /**
    * POST /store/order
    */
  def placeOrder(): Action[AnyContent] = Action { request =>
    def executeApi(): Order = {
      val body = request.body.asJson.map(_.as[Order]).getOrElse {
        throw new OpenApiExceptions.MissingRequiredParameterException("body", "body")
      }
      api.placeOrder(body)
    }

    val result = executeApi()
    val json = Json.toJson(result)
    Ok(json)
  }

  private def splitCollectionParam(paramValues: String, collectionFormat: String): List[String] = {
    val splitBy =
      collectionFormat match {
        case "csv" => ",+"
        case "tsv" => "\t+"
        case "ssv" => " +"
        case "pipes" => "|+"
      }

    paramValues.split(splitBy).toList
  }
}