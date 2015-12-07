(function($){
    $.fn.pager = function(options) {

        var opts = $.extend({}, $.fn.pager.defaults, options);
        return this.each(function() {

            // empty out the destination element and then render out the pager with the supplied options
            $(this).empty().append(renderpager(parseInt(opts.pagenumber), parseInt(opts.pagecount), opts.buttonClickCallback, parseInt(opts.pageSize), parseInt(opts.totalRecords)));


            // specify correct cursor activity
            $('.pages li ').mouseover(function() { document.body.style.cursor = "pointer"; }).mouseout(function() { document.body.style.cursor = "auto"; });
        });
    };
    // render and return the pager with the supplied options
    function renderpager(pagenumber, pagecount, buttonClickCallback, pageSize, totalRecords) {

        // setup $pager to hold render
        var $pager = $('<ul></ul>');

        pagecount=Math.ceil(totalRecords/pageSize);
        // add in the previous and next buttons
        $pager.append(renderButton('first', pagenumber, pagecount, buttonClickCallback)).append(renderButton('prev', pagenumber, pagecount, buttonClickCallback));

        // pager currently only handles 10 viewable pages ( could be easily parameterized, maybe in next version ) so handle edge cases
        var startPoint = 1;
        var endPoint = 9;

        if (pagenumber > 4) {
            startPoint = pagenumber - 4;
            endPoint = pagenumber + 4;
        }

        if (endPoint > pagecount) {
            startPoint = pagecount - 8;
            endPoint = pagecount;
        }

        if (startPoint < 1) {
            startPoint = 1;
        }

        // loop thru visible pages and render buttons
        for (var page = startPoint; page <= endPoint; page++) {

            var currentButton = $('<li><button class="btn btn-default btn-xs">' + (page) + '</button></li>');
            page == pagenumber ? currentButton.addClass('active') : currentButton.click(function() {
                buttonClickCallback(this.firstChild.firstChild.data);
            });
//          console.log(pagenumber)
            currentButton.appendTo($pager);
        }
        var bbarText='<li><span class="page_bar"><span class="page_bar_text">\u7b2c{0}/{1}\u9875,\u5f53\u524d\u663e\u793a{2}-{3}\u6761,\u5171{4}\u6761</span></li>';

        // render in the next and last buttons before returning the whole rendered control back.
        $pager.append(renderButton('next', pagenumber, pagecount, buttonClickCallback)).append(renderButton('last', pagenumber, pagecount, buttonClickCallback));
        $pager.append(bbarText.format(pagenumber,pagecount,(((pagenumber - 1) * pageSize) + 1),Math.min(pagenumber * pageSize, totalRecords),totalRecords));

        return $pager;
    }

    // renders and returns a 'specialized' button, ie 'next', 'previous' etc. rather than a page number button
    function renderButton(buttonLabel, pagenumber, pagecount, buttonClickCallback) {

        var destPage = 1;
        var labelText = '';
        // work out destination page for required button type
        switch (buttonLabel) {
            case "first":
                destPage = 1;
                labelText = '首页';
                break;
            case "prev":
                destPage = pagenumber - 1;
                labelText = '上一页';
                break;
            case "next":
                destPage = pagenumber + 1;
                labelText = '下一页';
                break;
            case "last":
                destPage = pagecount;
                labelText = '末页';
                break;
        }
        var $Button;
        if (labelText) {
            $Button = $('<li><button class="btn btn-default btn-xs">' + labelText + '</button></li>');
        } else {
            $Button = $('<li><button class="btn btn-default btn-xs">' + buttonLabel + '</button></li>');
        }
        // disable and 'grey' out buttons if not needed.
        if (buttonLabel == "first" || buttonLabel == "prev") {
            pagenumber <= 1 ? $Button.addClass('pgEmpty') : $Button.click(function() { buttonClickCallback(destPage); });
        }
        else {
            pagenumber >= pagecount ? $Button.addClass('pgEmpty') : $Button.click(function() { buttonClickCallback(destPage); });
        }

        return $Button;
    }
    $.fn.pager.defaults = {
        pagenumber: 1,
        pagecount: 1
    };

})(jQuery);





