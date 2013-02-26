package ds

import org.springframework.dao.DataIntegrityViolationException

class DealCategoryController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [dealCategoryInstanceList: DealCategory.list(params), dealCategoryInstanceTotal: DealCategory.count()]
    }

    def create() {
        [dealCategoryInstance: new DealCategory(params)]
    }

    def save() {
        def dealCategoryInstance = new DealCategory(params)
        if (!dealCategoryInstance.save(flush: true)) {
            render(view: "create", model: [dealCategoryInstance: dealCategoryInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'dealCategory.label', default: 'DealCategory'), dealCategoryInstance.id])
        redirect(action: "show", id: dealCategoryInstance.id)
    }

    def show(Long id) {
        def dealCategoryInstance = DealCategory.get(id)
        if (!dealCategoryInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'dealCategory.label', default: 'DealCategory'), id])
            redirect(action: "list")
            return
        }

        [dealCategoryInstance: dealCategoryInstance]
    }

    def edit(Long id) {
        def dealCategoryInstance = DealCategory.get(id)
        if (!dealCategoryInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'dealCategory.label', default: 'DealCategory'), id])
            redirect(action: "list")
            return
        }

        [dealCategoryInstance: dealCategoryInstance]
    }

    def update(Long id, Long version) {
        def dealCategoryInstance = DealCategory.get(id)
        if (!dealCategoryInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'dealCategory.label', default: 'DealCategory'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (dealCategoryInstance.version > version) {
                dealCategoryInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'dealCategory.label', default: 'DealCategory')] as Object[],
                          "Another user has updated this DealCategory while you were editing")
                render(view: "edit", model: [dealCategoryInstance: dealCategoryInstance])
                return
            }
        }

        dealCategoryInstance.properties = params

        if (!dealCategoryInstance.save(flush: true)) {
            render(view: "edit", model: [dealCategoryInstance: dealCategoryInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'dealCategory.label', default: 'DealCategory'), dealCategoryInstance.id])
        redirect(action: "show", id: dealCategoryInstance.id)
    }

    def delete(Long id) {
        def dealCategoryInstance = DealCategory.get(id)
        if (!dealCategoryInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'dealCategory.label', default: 'DealCategory'), id])
            redirect(action: "list")
            return
        }

        try {
            dealCategoryInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'dealCategory.label', default: 'DealCategory'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'dealCategory.label', default: 'DealCategory'), id])
            redirect(action: "show", id: id)
        }
    }
}
