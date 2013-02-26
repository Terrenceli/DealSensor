package ds



import org.junit.*
import grails.test.mixin.*

@TestFor(DealCategoryController)
@Mock(DealCategory)
class DealCategoryControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/dealCategory/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.dealCategoryInstanceList.size() == 0
        assert model.dealCategoryInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.dealCategoryInstance != null
    }

    void testSave() {
        controller.save()

        assert model.dealCategoryInstance != null
        assert view == '/dealCategory/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/dealCategory/show/1'
        assert controller.flash.message != null
        assert DealCategory.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/dealCategory/list'

        populateValidParams(params)
        def dealCategory = new DealCategory(params)

        assert dealCategory.save() != null

        params.id = dealCategory.id

        def model = controller.show()

        assert model.dealCategoryInstance == dealCategory
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/dealCategory/list'

        populateValidParams(params)
        def dealCategory = new DealCategory(params)

        assert dealCategory.save() != null

        params.id = dealCategory.id

        def model = controller.edit()

        assert model.dealCategoryInstance == dealCategory
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/dealCategory/list'

        response.reset()

        populateValidParams(params)
        def dealCategory = new DealCategory(params)

        assert dealCategory.save() != null

        // test invalid parameters in update
        params.id = dealCategory.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/dealCategory/edit"
        assert model.dealCategoryInstance != null

        dealCategory.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/dealCategory/show/$dealCategory.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        dealCategory.clearErrors()

        populateValidParams(params)
        params.id = dealCategory.id
        params.version = -1
        controller.update()

        assert view == "/dealCategory/edit"
        assert model.dealCategoryInstance != null
        assert model.dealCategoryInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/dealCategory/list'

        response.reset()

        populateValidParams(params)
        def dealCategory = new DealCategory(params)

        assert dealCategory.save() != null
        assert DealCategory.count() == 1

        params.id = dealCategory.id

        controller.delete()

        assert DealCategory.count() == 0
        assert DealCategory.get(dealCategory.id) == null
        assert response.redirectedUrl == '/dealCategory/list'
    }
}
