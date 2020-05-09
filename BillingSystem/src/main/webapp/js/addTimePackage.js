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
  <td class="pt-3-half" contenteditable="true">0000-00-00 00:00:00</td>
  <td class="pt-3-half" contenteditable="true">0000-00-00 00:00:00</td>
  <td class="pt-3-half">
    <select class="browser-default custom-select mb-4" id="select" name="type">
        <option value="" disabled="" selected="">Choose your option</option>
        <option value="Mon">Mon</option>
        <option value="Tue">Tue</option>
        <option value="Wed">Wed</option>
        <option value="Thu">Thu</option>
        <option value="Fri">Fri</option>
        <option value="Sat">Sat</option>
        <option value="Sun">Sun</option>
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
        }
else{
const $clone = $tableID.find('tbody tr').last();
        var newOrder = (parseInt($clone.find('td:eq(0)').text())) + 1;
        $('tbody').append(newTr);
        $tableID.find('tbody tr').last().find('td:eq(0)').html(newOrder);
        }
//  $tableID.find('table').append($clone);
});
        $tableID.on('click', '.table-remove', function () {
        var currentRow = $(this).closest('tr');
                var TimePackage = currentRow.attr("id");
                var clickedBtn = $(this);
                if (TimePackage === "0")
                $(this).parents('tr').detach();
                else{
                $.post('../deleteTimePackage', {
                TimePackage_id: TimePackage
                },
                        function (response) {
                        if (response === "success")
                                clickedBtn.parents('tr').detach();
                                alert(response);
                        });
                }
        });
        $tableID.on('click', '.table-submit', function () {
//           alert("t");
        var currentRow = $(this).closest('tr');
                var trid = $(this).closest('tr').attr('id');
                var timePackageName = currentRow.find('td:eq(1)').text();
                var start = currentRow.find('td:eq(2)').text();
                var finish = currentRow.find('td:eq(3)').text();
                var day = currentRow.find('td:eq(4)').children(0).children("option:selected").val();
//                alert("++" + ServiceName + "," + rating + "," + ratingf  + "," + type)
                //POST request
                $.post('../addtimePackage', {
                pkg_name: timePackageName,
                        start: start,
                        finish: finish,
                        day: day,
                },
                        function (response) {
                        if (response !== "failed"){
                        currentRow.attr("id", response);
                                alert("success, new ID = " + response);
                        }
                        else
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
