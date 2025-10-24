    function contextMenuItemOnclick(action, event) {
        alert(`You selected: ${action} : ${event.target.textContent}`);
        document.getElementById('customContextMenu').style.display = 'none';
    }
	function showTextEditor(event) {
   	
        const clickedElement = event.target;
        const elementId = clickedElement.id;
        const elementText = clickedElement.textContent;
        
        const parentElement = clickedElement.parentNode;
        
        const editorContainer = document.createElement("div");
        editorContainer.classList.add("editor-container");
        
        const textArea = document.createElement("textarea");
        textArea.setAttribute("id", "editor"+elementId);
        textArea.setAttribute("name", "editor"+elementId);
        textArea.style.width = "100%";
        textArea.style.height = "auto";
        textArea.classList.add("form-control");
        textArea.textContent = elementText;
        
        const saveIcon = document.createElement("i");
        saveIcon.classList.add("fa");
        saveIcon.classList.add("fa-save");
        
        const cancelIcon = document.createElement("i");
        cancelIcon.classList.add("fa");
        cancelIcon.classList.add("fa-undo");
        
        const saveButton = document.createElement("button");
        saveButton.classList.add("btn");
        saveButton.classList.add("btn-primary");
        saveButton.classList.add("mt-3");
        saveButton.textContent = " Save";
        saveButton.insertBefore(saveIcon, saveButton.firstChild);
        saveButton.addEventListener("click", function(event){
        	clickedElement.textContent = textArea.value;
        	
        	const jsonData = {
        		id:	elementId,
        		value: textArea.value
        	};
        	editorContainer.remove();
        	alert(JSON.stringify(jsonData));
        });
        
        const cancelButton = document.createElement("button");
        cancelButton.classList.add("btn");
        cancelButton.classList.add("btn-secondary");
        cancelButton.classList.add("mt-3");
        cancelButton.classList.add("mx-1");
        cancelButton.textContent = " Cancel";
        cancelButton.insertBefore(cancelIcon, cancelButton.firstChild);
        cancelButton.addEventListener("click", function(event){editorContainer.remove()});
        
        editorContainer.appendChild(textArea);
        editorContainer.appendChild(saveButton);
        editorContainer.appendChild(cancelButton);
        
        //parentElement.appendChild(editorContainer);
        parentElement.insertBefore(editorContainer, clickedElement.nextSibling);
	}
	
	function showContextMenu(event) {
        const clickedElement = event.target;
        const elementId = clickedElement.id;
        const elementText = clickedElement.textContent;
       
        const customContextMenu = document.createElement('div');
        customContextMenu.setAttribute("id", "customContextMenu");
        customContextMenu.classList.add("custom-context-menu");

		const menuList = document.createElement("ul");
		
		const addIcon = document.createElement("i");
		addIcon.classList.add("fa");
		addIcon.classList.add("fa-plus");
		
		const editIcon = document.createElement("i");
		editIcon.classList.add("fa");
		editIcon.classList.add("fa-pencil");
		
		const removeIcon = document.createElement("i");
		removeIcon.classList.add("fa");
		removeIcon.classList.add("fa-remove");
		
		const addOption = document.createElement("li");
		addOption.setAttribute("id", "addOption");
		addOption.textContent = " Add New";
		addOption.insertBefore(addIcon, addOption.firstChild);
		addOption.addEventListener("click", function(){
			contextMenuItemOnclick("Add New", event);
		});
		
		const editOption = document.createElement("li");
		editOption.setAttribute("id", "editOption");
		editOption.textContent = " Edit";
		editOption.insertBefore(editIcon, editOption.firstChild);
		editOption.addEventListener("click", function(){
			contextMenuItemOnclick("Edit", event);
		});
				
		const removeOption = document.createElement("li");
		removeOption.setAttribute("id", "removeOption");
		removeOption.textContent = " Remove";
		removeOption.insertBefore(removeIcon, removeOption.firstChild);
		removeOption.addEventListener("click", function(){
			contextMenuItemOnclick("Remove", event);
		});
		
		menuList.appendChild(addOption);
		menuList.appendChild(editOption);
		menuList.appendChild(removeOption);
		
		customContextMenu.appendChild(menuList);

        customContextMenu.style.display = "block";
        customContextMenu.style.left = `${event.pageX}px`;
        customContextMenu.style.top = `${event.pageY}px`;
        
        document.addEventListener('click', function onClickOutside() {
            customContextMenu.style.display = 'none';
            document.removeEventListener('click', onClickOutside);
        });

		document.body.appendChild(customContextMenu);
        
	}
	
	(() => {
		
		
		
		const paragraphs = document.querySelectorAll("div#summarySection p");
		paragraphs.forEach(p => {
			
			p.addEventListener('click', function(event) {
				event.preventDefault();
		    	document.querySelectorAll(".editor-container").forEach(element=>{
		    		element.remove();
		    	})
				showTextEditor(event);
		    });

		    p.addEventListener('contextmenu', function(event) {
		        event.preventDefault();
		        showContextMenu(event);
		    });			
		});
		
		document.addEventListener('DOMContentLoaded', (event) => {
			//define all even handlers for onload event here
		});
		
	})();
