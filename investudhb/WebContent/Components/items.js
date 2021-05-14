$(document).ready(function()
{
	if ($("#alertSuccess").text().trim() == "")
	{
	$("#alertSuccess").hide();
	}
	$("#alertError").hide();
});

//SAVE
$(document).on("click", "#btnSave", function(event)
	{
	// Clear alerts---------------------
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();
	
	// Form validation-------------------
	var status = validateItemForm();
	if (status != true)
	{
	$("#alertError").text(status);
	$("#alertError").show();
	return;
	}
	
	// If valid------------------------
	var type = ($("#hidInvestIDSave").val() == "") ? "POST" : "PUT";
	$.ajax(
	{
	url : "ItemsAPI",
	type : type,
	data : $("#formItem").serialize(),
	dataType : "text",
	complete : function(response, status)
	{
	onItemSaveComplete(response.responseText, status);
	}
	});
});


function onItemSaveComplete(response, status)
{
	if (status == "success")
	{
	var resultSet = JSON.parse(response);
	if (resultSet.status.trim() == "success")
	{
	$("#alertSuccess").text("Successfully saved.");
	$("#alertSuccess").show();
	$("#divItemsGrid").html(resultSet.data);
	} else if (resultSet.status.trim() == "error")
	{
	$("#alertError").text(resultSet.data);
	$("#alertError").show();
	}
	} else if (status == "error")
	{
	$("#alertError").text("Error while saving.");
	$("#alertError").show();
	} else
	{
	$("#alertError").text("Unknown error while saving..");
	$("#alertError").show();
	}
	$("#hidItemIDSave").val("");
	$("#formItem")[0].reset();
}


$(document).on("click", ".btnUpdate", function(event)
{
	$("#hidInvestIDSave").val($(this).data("investID"));
	$("#investName").val($(this).closest("tr").find('td:eq(0)').text());
	$("#reseacherID").val($(this).closest("tr").find('td:eq(1)').text());
	$("#investPrice").val($(this).closest("tr").find('td:eq(2)').text());
	$("#investDet").val($(this).closest("tr").find('td:eq(3)').text());
})


$(document).on("click", ".btnRemove", function(event)
{
	$.ajax(
	{
	url : "ItemsAPI",
	type : "DELETE",
	data : "investID=" + $(this).data("investID"),
	dataType : "text",
	complete : function(response, status)
	{
	onItemDeleteComplete(response.responseText, status);
	}
	});
})


function onItemDeleteComplete(response, status)
{
	if (status == "success")
	{
	var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success")
		{
		$("#alertSuccess").text("Successfully deleted.");
		$("#alertSuccess").show();
		$("#divItemsGrid").html(resultSet.data);
		} 
		else if (resultSet.status.trim() == "error")
		{
		$("#alertError").text(resultSet.data);
		$("#alertError").show();
		}
	} 
	else if (status == "error")
	{
	$("#alertError").text("Error while deleting.");
	$("#alertError").show();
	} 
	else
	{
	$("#alertError").text("Unknown error while deleting..");
	$("#alertError").show();
	}
}





// CLIENT-MODEL================================================================
function validateItemForm()
{
// CODE
	if ($("#investName").val().trim() == "")
	{
	return "Insert Invest name.";
	}
	// NAME
	if ($("#reseacherID").val().trim() == "")
	{
	return "Insert researcher ID.";
}

// PRICE-------------------------------
if ($("#investPrice").val().trim() == "")
{
return "Insert invest Price.";
}

// is numerical value
var tmpPrice = $("#investPrice").val().trim();
if (!$.isNumeric(tmpPrice))
{
return "Insert a numerical value for invest Price.";
}

// convert to decimal price
$("#investPrice").val(parseFloat(tmpPrice).toFixed(2));

// DESCRIPTION------------------------
if ($("#investDet").val().trim() == "")
{
return "Insert invest details.";
}
return true;
}