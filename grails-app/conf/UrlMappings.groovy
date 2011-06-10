class UrlMappings {
	static mappings = {
		"/contact/show/$contactId"(controller:'contact', action:'show') {}

		"/group/show/$groupId/contact/show/$contactId" {
			controller = 'contact'
			action = 'show'
		}
		"/group/show/$groupId"(controller:'contact') {}

		"/message/inbox"(controller:'message', action:'inbox') {}
		"/message/inbox/show/$id"(controller:'message', action:'show') {
			messageSection = 'inbox'
		}

		"/message/sent"(controller:'message', action:'sent') {}
		"/message/sent/show/$id"(controller:'message', action:'show') {
			messageSection = 'sent'
		}

		"/message/folder/$ownerId"(controller:'message') {}
		"/message/folder/$ownerId/show/$id"(controller:'message', action:'show') {
			messageSection = 'folder'
		}
		
		"/message/poll/$ownerId"(controller:'message') {}
		"/message/poll/$ownerId/show/$id"(controller:'message', action:'show') {
			messageSection = 'poll'
		}
		
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}

		"/"(view:"/index")
		"500"(view:'/error')
	}
}
