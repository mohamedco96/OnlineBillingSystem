/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

const $tableID = $('#table');
const $BTN = $('#export-btn');
const $EXPORT = $('#export');
const newTr = `
<tr class="hide" id="0">
  <td class="pt-3-half">1</td>
  <td class="pt-3-half" contenteditable="true"></td>
  <td class="pt-3-half" contenteditable="true"></td>
  <td class="pt-3-half" contenteditable="true"></td>


  <td>
  <span class="table-submit"><button type="button" class="btn btn-primary btn-rounded btn-sm my-0 waves-effect waves-light">Submit</button></span>
  </td>
  <td>
    <span class="table-remove"><button type="button" class="btn btn-danger btn-rounded btn-sm my-0 waves-effect waves-light">Remove</button></span>
  </td>
</tr>`;
$('.table-add').on('click', 'i', () => {

//  const $clone = $tableID.find('tbody tr').last().clone(true).removeClass('hide table-line');

    if ($tableID.find('tbody tr').length === 0) {

        $('tbody').append(newTr);
    } else {
        const $clone = $tableID.find('tbody tr').last();
        var newOrder = (parseInt($clone.find('td:eq(0)').text())) + 1;
        $('tbody').append(newTr);
        $tableID.find('tbody tr').last().find('td:eq(0)').html(newOrder);
    }
//  $tableID.find('table').append($clone);
});
$tableID.on('click', '.table-remove', function () {
    var currentRow = $(this).closest('tr');
    var tarrifZoneId = currentRow.attr("id");
    var clickedBtn = $(this);
    if (tarrifZoneId === "0")
        $(this).parents('tr').detach();
    else {
        $.post('../deleteTarrifZone', {
            tarrifZoneId: tarrifZoneId
        },
                function (response) {
                    if (response === "success"){
                        clickedBtn.parents('tr').detach();
//                    alert(response);
                }
                });
    }
});
$tableID.on('click', '.table-submit', function () {
//           alert("t");
    var currentRow = $(this).closest('tr');
    var trid = $(this).closest('tr').attr('id');
    var tarrifZoneName = currentRow.find('td:eq(1)').text();
    var sameNetwork = currentRow.find('td:eq(2)').text();
    var Local = currentRow.find('td:eq(3)').text();
    var Romaing = currentRow.find('td:eq(4)').text();
    //POST request
    $.post('../addTarrifZone', {
        tarrifZoneId:trid,
        tarrifZoneName: tarrifZoneName,
        sameNetwork: sameNetwork,
        Local: Local,
        Romaing: Romaing

    },
            function (response) {
                if (response !== "failed") {
                    currentRow.attr("id", response);
                    var para = document.createElement("P");
                    var t;
                    if (trid === "0")
                        t = document.createTextNode("Added " + tarrifZoneName + " Successfully");
                    else
                        t = document.createTextNode("Updated " + tarrifZoneName + " Successfully");
                    para.appendChild(t);
                    var x = document.getElementById("success");
                    x.innerHTML = '';
                    x.appendChild(para);
                    x.className = "show";
                    setTimeout(function () {
                        x.className = x.className.replace("show", "");
                    }, 3000);
                } else
                    alert("failed");
            });
});
// A few jQuery helpers for exporting only
jQuery.fn.pop = [].pop;
jQuery.fn.shift = [].shift;
$BTN.on('click', () => {

    const $rows = $tableID.find('tr:not(:hidden)');
    const headers = [];
    const data = [];
    // Get the headers (add special header logic here)
    $($rows.shift()).find('th:not(:empty)').each(function () {

        headers.push($(this).text().toLowerCase());
    });
    // Turn all existing rows into a loopable array
    $rows.each(function () {
        const $td = $(this).find('td');
        const h = {};
        // Use the headers from earlier to name our hash keys
        headers.forEach((header, i) => {

            h[header] = $td.eq(i).text();
        });
        data.push(h);
    });
    // Output the result
    $EXPORT.text(JSON.stringify(data));
});
