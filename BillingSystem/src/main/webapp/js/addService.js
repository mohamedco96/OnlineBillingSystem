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
  <td class="pt-3-half">
    <select class="browser-default custom-select mb-4" id="select" name="type">
        <option value="" disabled="" selected="">Choose your option</option>
        <option value="Voice">Voice</option>
        <option value="Data">Data</option>
        <option value="Sms">Sms</option>
        <option value="Recurring Services">Recurring Services</option>
        <option value="One time fee">One time fee</option>
    </select>
  </td>

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
                var serviceId = currentRow.attr("id");
                var ServiceName = currentRow.find('td:eq(1)').text();
                var clickedBtn = $(this);
                if (serviceId === "0")
                $(this).parents('tr').detach();
                else {
                $.post('../deleteService', {
                service_id: serviceId
                },
                        function (response) {
                        if (response === "success")
                                clickedBtn.parents('tr').detach();
                                var para = document.createElement("P");
                                var t = document.createTextNode("Delete "+ServiceName+" Successfully");
                                para.appendChild(t);
                                document.getElementById("delete").appendChild(para);
                                var x = document.getElementById("delete");
                                x.className = "show";
                                setTimeout(function () {
                                x.className = x.className.replace("show", "");
                                        }, 3000);
                        });
                }
        });
        $tableID.on('click', '.table-submit', function () {
//           alert("t");
        var currentRow = $(this).closest('tr');
                var trid = $(this).closest('tr').attr('id');
                var ServiceName = currentRow.find('td:eq(1)').text();
                var rating = currentRow.find('td:eq(2)').text();
                var type = currentRow.find('td:eq(3)').children(0).children("option:selected").val();
//                alert("++" + ServiceName + "," + rating + "," + type)
                //POST request
                $.post('../addService', {
                service_name: ServiceName,
                        rate: rating,
                        type: type,
                },
                        function (response) {
                        if (response !== "failed") {
                        currentRow.attr("id", response);
//                                alert("success, new ID = " + response);
                                var para = document.createElement("P");
                                var t = document.createTextNode("Add "+ServiceName+" Successfully");
                                para.appendChild(t);
                                document.getElementById("success").appendChild(para);
                                var x = document.getElementById("success");
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
