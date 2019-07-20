import com.nongped.core.CassandraService
import org.scalatest.FunSuite

class CassandraServiceTest extends FunSuite {
  test("connect should return the cassandra connection") {
    val cassandraService = new CassandraService()
    cassandraService.connect()
  }
}
