	$(document).ready(function() {
		$( ".empresas" ).change(function() { 
			var str = $("#empresa").val();
			$.ajax({
				type: "GET",
				url:"/periodos",
				data:{'nombre' : str},
				error: function(){
					alert("Error, recargue la página.");
				},
				success: function(data){
					$("#periodo").replaceWith(data);
				}
			});

		});	
	});

