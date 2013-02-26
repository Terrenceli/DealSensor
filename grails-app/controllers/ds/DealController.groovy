package ds

import org.springframework.dao.DataIntegrityViolationException

class DealController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [dealInstanceList: Deal.list(params), dealInstanceTotal: Deal.count()]
    }

    def create() {
        [dealInstance: new Deal(params)]
    }

    def save() {
        def dealInstance = new Deal(params)
        if (!dealInstance.save(flush: true)) {
            render(view: "create", model: [dealInstance: dealInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'deal.label', default: 'Deal'), dealInstance.id])
        redirect(action: "show", id: dealInstance.id)
    }

    def show(Long id) {
        def dealInstance = Deal.get(id)
        if (!dealInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'deal.label', default: 'Deal'), id])
            redirect(action: "list")
            return
        }

        [dealInstance: dealInstance]
    }

    def edit(Long id) {
        def dealInstance = Deal.get(id)
        if (!dealInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'deal.label', default: 'Deal'), id])
            redirect(action: "list")
            return
        }

        [dealInstance: dealInstance]
    }

    def update(Long id, Long version) {
        def dealInstance = Deal.get(id)
        if (!dealInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'deal.label', default: 'Deal'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (dealInstance.version > version) {
                dealInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'deal.label', default: 'Deal')] as Object[],
                          "Another user has updated this Deal while you were editing")
                render(view: "edit", model: [dealInstance: dealInstance])
                return
            }
        }

        dealInstance.properties = params

        if (!dealInstance.save(flush: true)) {
            render(view: "edit", model: [dealInstance: dealInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'deal.label', default: 'Deal'), dealInstance.id])
        redirect(action: "show", id: dealInstance.id)
    }

    def delete(Long id) {
        def dealInstance = Deal.get(id)
        if (!dealInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'deal.label', default: 'Deal'), id])
            redirect(action: "list")
            return
        }

        try {
            dealInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'deal.label', default: 'Deal'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'deal.label', default: 'Deal'), id])
            redirect(action: "show", id: id)
        }
    }
}
