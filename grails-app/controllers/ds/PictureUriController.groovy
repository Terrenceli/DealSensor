package ds

import org.springframework.dao.DataIntegrityViolationException

class PictureUriController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [pictureUriInstanceList: PictureUri.list(params), pictureUriInstanceTotal: PictureUri.count()]
    }

    def create() {
        [pictureUriInstance: new PictureUri(params)]
    }

    def save() {
        def pictureUriInstance = new PictureUri(params)
        if (!pictureUriInstance.save(flush: true)) {
            render(view: "create", model: [pictureUriInstance: pictureUriInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'pictureUri.label', default: 'PictureUri'), pictureUriInstance.id])
        redirect(action: "show", id: pictureUriInstance.id)
    }

    def show(Long id) {
        def pictureUriInstance = PictureUri.get(id)
        if (!pictureUriInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'pictureUri.label', default: 'PictureUri'), id])
            redirect(action: "list")
            return
        }

        [pictureUriInstance: pictureUriInstance]
    }

    def edit(Long id) {
        def pictureUriInstance = PictureUri.get(id)
        if (!pictureUriInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'pictureUri.label', default: 'PictureUri'), id])
            redirect(action: "list")
            return
        }

        [pictureUriInstance: pictureUriInstance]
    }

    def update(Long id, Long version) {
        def pictureUriInstance = PictureUri.get(id)
        if (!pictureUriInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'pictureUri.label', default: 'PictureUri'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (pictureUriInstance.version > version) {
                pictureUriInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'pictureUri.label', default: 'PictureUri')] as Object[],
                          "Another user has updated this PictureUri while you were editing")
                render(view: "edit", model: [pictureUriInstance: pictureUriInstance])
                return
            }
        }

        pictureUriInstance.properties = params

        if (!pictureUriInstance.save(flush: true)) {
            render(view: "edit", model: [pictureUriInstance: pictureUriInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'pictureUri.label', default: 'PictureUri'), pictureUriInstance.id])
        redirect(action: "show", id: pictureUriInstance.id)
    }

    def delete(Long id) {
        def pictureUriInstance = PictureUri.get(id)
        if (!pictureUriInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'pictureUri.label', default: 'PictureUri'), id])
            redirect(action: "list")
            return
        }

        try {
            pictureUriInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'pictureUri.label', default: 'PictureUri'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'pictureUri.label', default: 'PictureUri'), id])
            redirect(action: "show", id: id)
        }
    }
}
