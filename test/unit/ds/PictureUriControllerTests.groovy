package ds



import org.junit.*
import grails.test.mixin.*

@TestFor(PictureUriController)
@Mock(PictureUri)
class PictureUriControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/pictureUri/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.pictureUriInstanceList.size() == 0
        assert model.pictureUriInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.pictureUriInstance != null
    }

    void testSave() {
        controller.save()

        assert model.pictureUriInstance != null
        assert view == '/pictureUri/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/pictureUri/show/1'
        assert controller.flash.message != null
        assert PictureUri.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/pictureUri/list'

        populateValidParams(params)
        def pictureUri = new PictureUri(params)

        assert pictureUri.save() != null

        params.id = pictureUri.id

        def model = controller.show()

        assert model.pictureUriInstance == pictureUri
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/pictureUri/list'

        populateValidParams(params)
        def pictureUri = new PictureUri(params)

        assert pictureUri.save() != null

        params.id = pictureUri.id

        def model = controller.edit()

        assert model.pictureUriInstance == pictureUri
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/pictureUri/list'

        response.reset()

        populateValidParams(params)
        def pictureUri = new PictureUri(params)

        assert pictureUri.save() != null

        // test invalid parameters in update
        params.id = pictureUri.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/pictureUri/edit"
        assert model.pictureUriInstance != null

        pictureUri.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/pictureUri/show/$pictureUri.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        pictureUri.clearErrors()

        populateValidParams(params)
        params.id = pictureUri.id
        params.version = -1
        controller.update()

        assert view == "/pictureUri/edit"
        assert model.pictureUriInstance != null
        assert model.pictureUriInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/pictureUri/list'

        response.reset()

        populateValidParams(params)
        def pictureUri = new PictureUri(params)

        assert pictureUri.save() != null
        assert PictureUri.count() == 1

        params.id = pictureUri.id

        controller.delete()

        assert PictureUri.count() == 0
        assert PictureUri.get(pictureUri.id) == null
        assert response.redirectedUrl == '/pictureUri/list'
    }
}
