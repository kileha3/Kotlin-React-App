package util

import kotlinx.browser.window
import modal.Entry

object AppUtils {

    private val ENTRIES = JSON.parse<Array<Entry>>("[{\n" +
            "  \"name\": \"The Hour of the Lynx\",\n" +
            "  \"id\": \"e50ebe01-e7e1-40ba-a1d4-91c3dbafccb3\",\n" +
            "  \"description\": \"Morbi porttitor lorem id ligula. Suspendisse ornare consequat lectus. In est risus, auctor sed, tristique in, tempus sit amet, sem.\",\n" +
            "  \"thumbnail\": \"http://dummyimage.com/250x250.jpg/5fa2dd/ffffff\"\n" +
            "}, {\n" +
            "  \"name\": \"Little Hamlet\",\n" +
            "  \"id\": \"46dece3a-c965-44a9-9328-bc5b738b8275\",\n" +
            "  \"description\": \"In hac habitasse platea dictumst. Etiam faucibus cursus urna. Ut tellus.\",\n" +
            "  \"thumbnail\": \"http://dummyimage.com/250x250.jpg/cc0000/ffffff\"\n" +
            "}, {\n" +
            "  \"name\": \"Classmates\",\n" +
            "  \"id\": \"bbc0e7ce-2f80-44d1-bb46-7320ddc8f1fe\",\n" +
            "  \"description\": \"In congue. Etiam justo. Etiam pretium iaculis justo.\\n\\nIn hac habitasse platea dictumst. Etiam faucibus cursus urna. Ut tellus.\",\n" +
            "  \"thumbnail\": \"http://dummyimage.com/250x250.jpg/dddddd/000000\"\n" +
            "}, {\n" +
            "  \"name\": \"Bench, The (Bænken)\",\n" +
            "  \"id\": \"e92b7fba-a324-456a-9024-be67ff127c6b\",\n" +
            "  \"description\": \"Maecenas ut massa quis augue luctus tincidunt. Nulla mollis molestie lorem. Quisque ut erat.\",\n" +
            "  \"thumbnail\": \"http://dummyimage.com/250x250.jpg/ff4444/ffffff\"\n" +
            "}, {\n" +
            "  \"name\": \"Robots\",\n" +
            "  \"id\": \"2247a29b-becd-4c83-8014-b57c9771a819\",\n" +
            "  \"description\": \"Maecenas ut massa quis augue luctus tincidunt. Nulla mollis molestie lorem. Quisque ut erat.\\n\\nCurabitur gravida nisi at nibh. In hac habitasse platea dictumst. Aliquam augue quam, sollicitudin vitae, consectetuer eget, rutrum at, lorem.\\n\\nInteger tincidunt ante vel ipsum. Praesent blandit lacinia erat. Vestibulum sed magna at nunc commodo placerat.\",\n" +
            "  \"thumbnail\": \"http://dummyimage.com/250x250.jpg/cc0000/ffffff\"\n" +
            "}, {\n" +
            "  \"name\": \"Cleanskin\",\n" +
            "  \"id\": \"b9a6c066-128e-44d6-bbb1-44ff2a2a49f1\",\n" +
            "  \"description\": \"Sed ante. Vivamus tortor. Duis mattis egestas metus.\\n\\nAenean fermentum. Donec ut mauris eget massa tempor convallis. Nulla neque libero, convallis eget, eleifend luctus, ultricies eu, nibh.\",\n" +
            "  \"thumbnail\": \"http://dummyimage.com/250x250.jpg/5fa2dd/ffffff\"\n" +
            "}, {\n" +
            "  \"name\": \"Celebration, The (Festen)\",\n" +
            "  \"id\": \"89cdef10-c69f-4438-b065-f9f999a65057\",\n" +
            "  \"description\": \"Phasellus in felis. Donec semper sapien a libero. Nam dui.\\n\\nProin leo odio, porttitor id, consequat in, consequat ut, nulla. Sed accumsan felis. Ut at dolor quis odio consequat varius.\\n\\nInteger ac leo. Pellentesque ultrices mattis odio. Donec vitae nisi.\",\n" +
            "  \"thumbnail\": \"http://dummyimage.com/250x250.jpg/5fa2dd/ffffff\"\n" +
            "}, {\n" +
            "  \"name\": \"Police Academy 3: Back in Training\",\n" +
            "  \"id\": \"f0fb5b30-f642-4d47-b8d8-359efaa21a78\",\n" +
            "  \"description\": \"In sagittis dui vel nisl. Duis ac nibh. Fusce lacus purus, aliquet at, feugiat non, pretium quis, lectus.\\n\\nSuspendisse potenti. In eleifend quam a odio. In hac habitasse platea dictumst.\",\n" +
            "  \"thumbnail\": \"http://dummyimage.com/250x250.jpg/cc0000/ffffff\"\n" +
            "}, {\n" +
            "  \"name\": \"Bounty Hunters (Bail Enforcers)\",\n" +
            "  \"id\": \"3b4f115c-0698-4b31-bc67-fe3a7e0d968d\",\n" +
            "  \"description\": \"Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Vivamus vestibulum sagittis sapien. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.\\n\\nEtiam vel augue. Vestibulum rutrum rutrum neque. Aenean auctor gravida sem.\\n\\nPraesent id massa id nisl venenatis lacinia. Aenean sit amet justo. Morbi ut odio.\",\n" +
            "  \"thumbnail\": \"http://dummyimage.com/250x250.jpg/cc0000/ffffff\"\n" +
            "}, {\n" +
            "  \"name\": \"Cat People\",\n" +
            "  \"id\": \"ce471c1a-2992-4fc2-81b3-64270270be66\",\n" +
            "  \"description\": \"Suspendisse potenti. In eleifend quam a odio. In hac habitasse platea dictumst.\\n\\nMaecenas ut massa quis augue luctus tincidunt. Nulla mollis molestie lorem. Quisque ut erat.\",\n" +
            "  \"thumbnail\": \"http://dummyimage.com/250x250.jpg/ff4444/ffffff\"\n" +
            "}, {\n" +
            "  \"name\": \"Last of the Dogmen\",\n" +
            "  \"id\": \"4542a285-cd8d-4574-94d8-ec0a44d8004c\",\n" +
            "  \"description\": \"Duis consequat dui nec nisi volutpat eleifend. Donec ut dolor. Morbi vel lectus in quam fringilla rhoncus.\\n\\nMauris enim leo, rhoncus sed, vestibulum sit amet, cursus id, turpis. Integer aliquet, massa id lobortis convallis, tortor risus dapibus augue, vel accumsan tellus nisi eu orci. Mauris lacinia sapien quis libero.\\n\\nNullam sit amet turpis elementum ligula vehicula consequat. Morbi a ipsum. Integer a nibh.\",\n" +
            "  \"thumbnail\": \"http://dummyimage.com/250x250.jpg/cc0000/ffffff\"\n" +
            "}, {\n" +
            "  \"name\": \"Oh, Woe Is Me (Hélas pour moi)\",\n" +
            "  \"id\": \"26033dc4-363a-4179-ad92-9f43693e794c\",\n" +
            "  \"description\": \"Proin interdum mauris non ligula pellentesque ultrices. Phasellus id sapien in sapien iaculis congue. Vivamus metus arcu, adipiscing molestie, hendrerit at, vulputate vitae, nisl.\\n\\nAenean lectus. Pellentesque eget nunc. Donec quis orci eget orci vehicula condimentum.\\n\\nCurabitur in libero ut massa volutpat convallis. Morbi odio odio, elementum eu, interdum eu, tincidunt in, leo. Maecenas pulvinar lobortis est.\",\n" +
            "  \"thumbnail\": \"http://dummyimage.com/250x250.jpg/cc0000/ffffff\"\n" +
            "}, {\n" +
            "  \"name\": \"Book of Love\",\n" +
            "  \"id\": \"aee24744-8b2b-4bb7-8f7f-627c7b5007cb\",\n" +
            "  \"description\": \"Aenean fermentum. Donec ut mauris eget massa tempor convallis. Nulla neque libero, convallis eget, eleifend luctus, ultricies eu, nibh.\\n\\nQuisque id justo sit amet sapien dignissim vestibulum. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Nulla dapibus dolor vel est. Donec odio justo, sollicitudin ut, suscipit a, feugiat et, eros.\",\n" +
            "  \"thumbnail\": \"http://dummyimage.com/250x250.jpg/dddddd/000000\"\n" +
            "}, {\n" +
            "  \"name\": \"Zombie Girl: The Movie\",\n" +
            "  \"id\": \"9513fdac-d1a4-4b9c-8610-92485a910776\",\n" +
            "  \"description\": \"In hac habitasse platea dictumst. Morbi vestibulum, velit id pretium iaculis, diam erat fermentum justo, nec condimentum neque sapien placerat ante. Nulla justo.\",\n" +
            "  \"thumbnail\": \"http://dummyimage.com/250x250.jpg/5fa2dd/ffffff\"\n" +
            "}, {\n" +
            "  \"name\": \"Big Knife, The\",\n" +
            "  \"id\": \"09acf722-1bcd-48ff-b43b-dfb28571aff0\",\n" +
            "  \"description\": \"Duis bibendum. Morbi non quam nec dui luctus rutrum. Nulla tellus.\\n\\nIn sagittis dui vel nisl. Duis ac nibh. Fusce lacus purus, aliquet at, feugiat non, pretium quis, lectus.\",\n" +
            "  \"thumbnail\": \"http://dummyimage.com/250x250.jpg/dddddd/000000\"\n" +
            "}, {\n" +
            "  \"name\": \"Clean\",\n" +
            "  \"id\": \"129a342c-0926-4a3a-ba67-65ae958f03db\",\n" +
            "  \"description\": \"Proin leo odio, porttitor id, consequat in, consequat ut, nulla. Sed accumsan felis. Ut at dolor quis odio consequat varius.\\n\\nInteger ac leo. Pellentesque ultrices mattis odio. Donec vitae nisi.\",\n" +
            "  \"thumbnail\": \"http://dummyimage.com/250x250.jpg/5fa2dd/ffffff\"\n" +
            "}, {\n" +
            "  \"name\": \"Operation Daybreak\",\n" +
            "  \"id\": \"48cd1f60-ab1b-4d3d-9520-dcbb38fafdf1\",\n" +
            "  \"description\": \"Sed sagittis. Nam congue, risus semper porta volutpat, quam pede lobortis ligula, sit amet eleifend pede libero quis orci. Nullam molestie nibh in lectus.\",\n" +
            "  \"thumbnail\": \"http://dummyimage.com/250x250.jpg/5fa2dd/ffffff\"\n" +
            "}, {\n" +
            "  \"name\": \"Bling Ring, The\",\n" +
            "  \"id\": \"f9f16fdf-1741-4152-901d-685811fc9635\",\n" +
            "  \"description\": \"Aliquam quis turpis eget elit sodales scelerisque. Mauris sit amet eros. Suspendisse accumsan tortor quis turpis.\\n\\nSed ante. Vivamus tortor. Duis mattis egestas metus.\\n\\nAenean fermentum. Donec ut mauris eget massa tempor convallis. Nulla neque libero, convallis eget, eleifend luctus, ultricies eu, nibh.\",\n" +
            "  \"thumbnail\": \"http://dummyimage.com/250x250.jpg/dddddd/000000\"\n" +
            "}, {\n" +
            "  \"name\": \"Mum & Dad\",\n" +
            "  \"id\": \"3ae73a0f-ebb1-4152-8700-5f95adc7d6c7\",\n" +
            "  \"description\": \"Maecenas ut massa quis augue luctus tincidunt. Nulla mollis molestie lorem. Quisque ut erat.\\n\\nCurabitur gravida nisi at nibh. In hac habitasse platea dictumst. Aliquam augue quam, sollicitudin vitae, consectetuer eget, rutrum at, lorem.\",\n" +
            "  \"thumbnail\": \"http://dummyimage.com/250x250.jpg/ff4444/ffffff\"\n" +
            "}, {\n" +
            "  \"name\": \"Burning Plain, The\",\n" +
            "  \"id\": \"aa4f58e0-570f-4b72-a6d6-9cec0b5d0ace\",\n" +
            "  \"description\": \"Praesent blandit. Nam nulla. Integer pede justo, lacinia eget, tincidunt eget, tempus vel, pede.\",\n" +
            "  \"thumbnail\": \"http://dummyimage.com/250x250.jpg/5fa2dd/ffffff\"\n" +
            "}]")

    val TRANSLATION = "{\n" +
            "  \"en-US\":{\n" +
            "    \"content\":\"Content\",\n" +
            "    \"schools\":\"Schools\",\n" +
            "    \"direction\":\"RTL SUpport\",\n" +
            "    \"language\":\"Use Swahili\",\n" +
            "    \"theme\":\"Swicth Theme\"\n" +
            "  },\n" +
            "  \n" +
            "  \"sw-Tz\":{\n" +
            "    \"content\":\"Yaliyomo\",\n" +
            "    \"schools\":\"Shule\",\n" +
            "    \"direction\":\"Msaada wa RTL\",\n" +
            "    \"language\":\"Use English\",\n" +
            "    \"theme\":\"Badili Mandhari\"\n" +
            "  }\n" +
            "}"

    fun go(destination: String, args: Map<String,dynamic> = mapOf()){
        val params = if(args.isEmpty())  "" else "?"+ args.entries.
        joinToString("&") { p -> p.key + "=" + p.value.toString()}
        window.location.assign("#/${destination}${params}")
    }

    fun entryList(numOfItems: Int = 6): List<Entry>{
        return ENTRIES.asList().subList(0,numOfItems)
    }

    private fun isWhiteSpace(c: Char): Boolean {
        return c == ' ' || c == '\n' || c == '\t' || c == '\r'
    }

    fun getParamFromUrl(paramKey: String): String ?{
        val params = window.location.hash.split("?")[1]
        return parseURLQueryString(params)[paramKey]
    }

    private fun parseURLQueryString(urlQuery: String): Map<String, String> {
        var retVal = urlQuery
        val queryPos = retVal.indexOf('?')
        if (queryPos != -1) {
            retVal = retVal.substring(queryPos + 1)
        }

        val parsedParams = parseParams(retVal, '&')

        return parsedParams.map {it.key to it.value}.toMap()
    }

    private fun parseParams(str: String, deliminator: Char): Map<String, String> {
        var paramName: String? = null
        val params = HashMap<String, String>()
        var inQuotes = false

        val strLen = str.length
        var sb = StringBuilder()
        var c: Char

        var lastChar: Char = 0.toChar()
        for (i in 0 until strLen) {
            c = str[i]
            if (c == '"') {
                if (!inQuotes) {
                    inQuotes = true
                } else if (inQuotes && lastChar != '\\') {
                    inQuotes = false
                }

            }

            if (isWhiteSpace(c) && !inQuotes || c == '"' && i < strLen - 1) {
                //do nothing more
            } else if (c == deliminator || i == strLen - 1) {
                //check if we are here because it's the end... then we add this to bufer
                if (i == strLen - 1 && c != '"') {
                    sb.append(c)
                }

                if (paramName != null) {
                    //this is a parameter with a value
                    params[paramName] = sb.toString()
                } else {
                    //this is a parameter on its own
                    params[sb.toString()] = ""
                }

                sb = StringBuilder()
                paramName = null
            } else if (c == '=') {
                paramName = sb.toString()
                sb = StringBuilder()
            } else {
                sb.append(c)
            }

            lastChar = c
        }

        return params
    }

    fun getEntry(entryId:String?): Entry{
       return ENTRIES.first{ it.id  == entryId}
    }

    fun getTranslations(locale: String): dynamic{
        val translations = JSON.parse<dynamic>(TRANSLATION)
        return translations[locale]
    }
}