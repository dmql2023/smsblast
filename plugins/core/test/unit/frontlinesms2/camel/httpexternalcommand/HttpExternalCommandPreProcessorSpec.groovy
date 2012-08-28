package frontlinesms2.camel.httpexternalcommand

import spock.lang.*
import frontlinesms2.*
import frontlinesms2.camel.*

import grails.buildtestdata.mixin.Build

@Mock(HttpExternalCommandFconnection)
@Build(HttpExternalCommandFconnection)
class HttpExternalCommandPreProcessorSpec extends CamelUnitSpecification {
	HttpExternalCommandPreProcessor p
	
	def setup() {
		def username = new RequestParameter(name:'username',value:'bob').save(failOnError:true)
		def password = new RequestParameter(name:'password',value:'secret').save(failOnError:true)
		def extra = new RequestParameter(name:'extrainfo',value:'extra extra').save(failOnError:true)
		def c = new HttpExternalCommandFconnection(url:"www.frontlinesms.com/sync")
		c.requestParameters << username << password << extra
		c.save(failOnError:true)
		p = new HttpExternalCommandPreProcessor()
	}

	def "out_body should have all the parameters defined in the external command fconnection"(){
		given:
			def x = mockExchange("simple")
		when:
			p.process(x)
		then:
			1 * x.out.setBody("simple")
			1 * x.out.headers.'frontlinesms.dispatch.id' == '45678'
			x.out.headers.'username' == 'bob'
			x.out.headers.'password' == 'secret'
			x.out.headers.'extra' == 'Kenya'
	}
	
	def 'out_body should be URL-encoded'() {
		given:
			def x = mockExchange("more complex")
		when:
			p.process(x)
		then:
			1 * x.out.setBody("more+complex")
	}
}