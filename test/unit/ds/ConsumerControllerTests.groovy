package ds



import org.junit.*
import grails.test.mixin.*

@TestFor(ConsumerController)
@Mock(Consumer)
class ConsumerControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/consumer/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.consumerInstanceList.size() == 0
        assert model.consumerInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.consumerInstance != null
    }

    void testSave() {
        controller.save()

        assert model.consumerInstance != null
        assert view == '/consumer/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/consumer/show/1'
        assert controller.flash.message != null
        assert Consumer.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/consumer/list'

        populateValidParams(params)
        def consumer = new Consumer(params)

        assert consumer.save() != null

        params.id = consumer.id

        def model = controller.show()

        assert model.consumerInstance == consumer
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/consumer/list'

        populateValidParams(params)
        def consumer = new Consumer(params)

        assert consumer.save() != null

        params.id = consumer.id

        def model = controller.edit()

        assert model.consumerInstance == consumer
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/consumer/list'

        response.reset()

        populateValidParams(params)
        def consumer = new Consumer(params)

        assert consumer.save() != null

        // test invalid parameters in update
        params.id = consumer.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/consumer/edit"
        assert model.consumerInstance != null

        consumer.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/consumer/show/$consumer.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        consumer.clearErrors()

        populateValidParams(params)
        params.id = consumer.id
        params.version = -1
        controller.update()

        assert view == "/consumer/edit"
        assert model.consumerInstance != null
        assert model.consumerInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/consumer/list'

        response.reset()

        populateValidParams(params)
        def consumer = new Consumer(params)

        assert consumer.save() != null
        assert Consumer.count() == 1

        params.id = consumer.id

        controller.delete()

        assert Consumer.count() == 0
        assert Consumer.get(consumer.id) == null
        assert response.redirectedUrl == '/consumer/list'
    }
}
