package ds



import org.junit.*
import grails.test.mixin.*

@TestFor(DealController)
@Mock(Deal)
class DealControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/deal/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.dealInstanceList.size() == 0
        assert model.dealInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.dealInstance != null
    }

    void testSave() {
        controller.save()

        assert model.dealInstance != null
        assert view == '/deal/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/deal/show/1'
        assert controller.flash.message != null
        assert Deal.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/deal/list'

        populateValidParams(params)
        def deal = new Deal(params)

        assert deal.save() != null

        params.id = deal.id

        def model = controller.show()

        assert model.dealInstance == deal
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/deal/list'

        populateValidParams(params)
        def deal = new Deal(params)

        assert deal.save() != null

        params.id = deal.id

        def model = controller.edit()

        assert model.dealInstance == deal
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/deal/list'

        response.reset()

        populateValidParams(params)
        def deal = new Deal(params)

        assert deal.save() != null

        // test invalid parameters in update
        params.id = deal.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/deal/edit"
        assert model.dealInstance != null

        deal.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/deal/show/$deal.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        deal.clearErrors()

        populateValidParams(params)
        params.id = deal.id
        params.version = -1
        controller.update()

        assert view == "/deal/edit"
        assert model.dealInstance != null
        assert model.dealInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/deal/list'

        response.reset()

        populateValidParams(params)
        def deal = new Deal(params)

        assert deal.save() != null
        assert Deal.count() == 1

        params.id = deal.id

        controller.delete()

        assert Deal.count() == 0
        assert Deal.get(deal.id) == null
        assert response.redirectedUrl == '/deal/list'
    }
}
