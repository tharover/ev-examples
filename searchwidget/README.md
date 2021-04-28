The HTML files in this folder may be useful for Engineering Village users who want to embed a search widget on their site.  

File descriptions:

<ul>
<li> searchwidget.html - this file can render a search widget for any EV database combination.  </li>
<li> searchwidget_cpx_ins_geo_grf.html - this file is an example of how to hard-code a database combination.</li>
</ul>

Usage instructions:
<ul>
<li>Either file can be copied as-is or in pieces and placed directly into an existing web page.  </li>
<li>Files can be referenced in an iframe, e.g. &lt;iframe src="https://static.engineeringvillage.com/widget/searchwidget.html?database=cpx,ins" id="ev_searchwidget"&gt;&lt;/iframe&gt;</li>
<li>The searchwidget.html file can take a "database" parameter with the following parameters:
    <ul>
        <li>"all" - All databases available on EV will be searched</li>
        <li>"cpx" - Just Compendex will be searched</li>
        <li>"cpx,ins" - Compendex + Inspec will be searched</li>
        <li>The complete list of database codes is available at the bottom of the HTML file in the javascript code</li>
    </ul>
</li>
</ul> 