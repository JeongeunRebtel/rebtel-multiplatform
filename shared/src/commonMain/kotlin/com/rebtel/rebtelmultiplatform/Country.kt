package com.rebtel.rebtelmultiplatform

import kotlin.math.min


data class Country(
    val isoCode: String,
    val dialingCodes: Array<String>
)

/**
 * Collection of mappings providing access to Countries by a specific key or query.
 */
object Countries {

    private var maxCountryCodeLength = 5

    /**
     * Mapping which returns a [Country] by its ISO code (2-letter code, such as "SE" for Sweden). The ISO code is case-insensitive.
     *
     * Usage: `Countries.byIsoCode["SE"]`
     */
    val byIsoCode = Mapping(
        addCountry = { country, map -> map[country.isoCode] = country },
        get = { map, key -> map[key.uppercase()] }
    )

    /**
     * Mapping which returns countries based on the dialing code of the provided phone number. Returns null if the number doesn't start with
     * a known dialing code, is in a non-international format or contains formatting characters.
     *
     * Usage: `Countries.byDialingCode["+46761234567"]`
     */
    val byDialingCode = Mapping(
        addCountry = { country, map ->
            country.dialingCodes.forEach {
                map[it] = country
                if (it.length > maxCountryCodeLength) maxCountryCodeLength = it.length
            }
        },
        get = { map, key ->
            val trimmedKey = key
            if (trimmedKey.length == key.length) {
                // Number wasn't in international format, so don't return any country
                return@Mapping null
            }

            for (i in min(maxCountryCodeLength, trimmedKey.length) downTo 1) {
                map[trimmedKey.substring(0, i)]?.also { return@Mapping it }
            }

            return@Mapping null
        }
    )

    class Mapping(
        private val addCountry: (Country, MutableMap<String, Country>) -> Unit,
        private val get: (Map<String, Country>, String) -> Country?
    ) {
        private val mapping: Map<String, Country> by lazy {
            mutableMapOf<String, Country>().apply {
                allCountries.forEach {
                    addCountry(it, this)
                }
            }
        }

        operator fun get(key: String) = get(mapping, key)
    }
}

/**
 * All existing countries as an unsorted array. Not for direct usage. A [Countries.Mapping] should be created instead providing access to
 * countries via a specific key.
 */
val allCountries: Array<Country> by lazy {
    arrayOf(
        Country("AC", arrayOf("247")),
        Country("AF", arrayOf("93")),
        Country("AL", arrayOf("355")),
        Country("DZ", arrayOf("213")),
        Country("AS", arrayOf("1684")),
        Country("AD", arrayOf("376")),
        Country("AO", arrayOf("244")),
        Country("AI", arrayOf("1264")),
        Country("AQ", arrayOf("672")),
        Country("AG", arrayOf("1268")),
        Country("AR", arrayOf("54")),
        Country(
            "AM",
            arrayOf(
                "374",
                "88210",
                "88212",
                "88213",
                "88214",
                "88215",
                "88216",
                "88220",
                "88222",
                "88223",
                "88224",
                "88228",
                "88229",
                "88230",
                "88231",
                "88232",
                "88233",
                "88235",
                "88236",
                "88237",
                "88238",
                "88239",
                "88240",
                "88241",
                "88242",
                "88298",
                "88299"
            )
        ),
        Country("AW", arrayOf("297")),
        Country("AU", arrayOf("61", "6723")),
        Country("AT", arrayOf("43")),
        Country("AZ", arrayOf("994")),
        Country("BS", arrayOf("1242")),
        Country("BH", arrayOf("973")),
        Country("BD", arrayOf("880")),
        Country("BB", arrayOf("1246")),
        Country("BY", arrayOf("375")),
        Country("BE", arrayOf("32", "388")),
        Country("BZ", arrayOf("501")),
        Country("BJ", arrayOf("229")),
        Country("BM", arrayOf("1441")),
        Country("BT", arrayOf("975")),
        Country("BO", arrayOf("591")),
        Country("BQ", arrayOf("5997")),
        Country("BA", arrayOf("387")),
        Country("BW", arrayOf("267")),
        Country("BR", arrayOf("55")),
        Country("BN", arrayOf("673")),
        Country("BG", arrayOf("359")),
        Country("BF", arrayOf("226")),
        Country("BI", arrayOf("257")),
        Country("KH", arrayOf("855")),
        Country("CM", arrayOf("237")),
        Country(
            "CA",
            arrayOf(
                "1204",
                "1226",
                "1250",
                "1289",
                "1306",
                "1343",
                "1403",
                "1416",
                "1418",
                "1438",
                "1450",
                "1506",
                "1514",
                "1519",
                "1579",
                "1581",
                "1587",
                "1600",
                "1604",
                "1613",
                "1647",
                "1705",
                "1709",
                "1778",
                "1780",
                "1807",
                "1819",
                "1867",
                "1902",
                "1905"
            )
        ),
        Country("CV", arrayOf("238")),
        Country("KY", arrayOf("1345")),
        Country("CF", arrayOf("236")),
        Country("TD", arrayOf("235")),
        Country("CL", arrayOf("56")),
        Country(
            "CN",
            arrayOf(
                "86",
                "86631",
                "86632",
                "86633",
                "86634",
                "86635",
                "86660",
                "86662",
                "86663",
                "86668",
                "86691",
                "86692"
            )
        ),
        Country("CO", arrayOf("57")),
        Country("KM", arrayOf("269")),
        Country("CG", arrayOf("242")),
        Country("CD", arrayOf("243")),
        Country("CK", arrayOf("682")),
        Country("CR", arrayOf("506")),
        Country("CI", arrayOf("225")),
        Country("HR", arrayOf("385")),
        Country("CU", arrayOf("53")),
        Country("CW", arrayOf("5999")),
        Country(
            "CY",
            arrayOf(
                "357",
                "90392",
                "905428",
                "905469",
                "905488"
            )
        ),
        Country("CZ", arrayOf("420")),
        Country("DK", arrayOf("45")),
        Country("DJ", arrayOf("253")),
        Country("DM", arrayOf("1767")),
        Country(
            "DO",
            arrayOf("1809", "1829", "1849")
        ),
        Country("EC", arrayOf("593")),
        Country("EG", arrayOf("20")),
        Country("SV", arrayOf("503")),
        Country("GQ", arrayOf("240")),
        Country("ER", arrayOf("291")),
        Country("EE", arrayOf("372")),
        Country("ET", arrayOf("251")),
        Country("FK", arrayOf("500")),
        Country("FO", arrayOf("298")),
        Country("FJ", arrayOf("679")),
        Country("FI", arrayOf("358")),
        Country("FR", arrayOf("33", "26226200")),
        Country("GF", arrayOf("594")),
        Country(
            "PF",
            arrayOf(
                "689",
                "34856096",
                "34856254",
                "34856609",
                "34856907",
                "34956049",
                "34956064",
                "34956103",
                "34956117",
                "349561295",
                "34956238",
                "349564699",
                "3495650",
                "3495651",
                "3495652",
                "34956928",
                "34956984"
            )
        ),
        Country("GA", arrayOf("241")),
        Country("GM", arrayOf("220")),
        Country("GE", arrayOf("995")),
        Country("DE", arrayOf("49")),
        Country("DG", arrayOf("246")),
        Country("GH", arrayOf("233")),
        Country(
            "GI",
            arrayOf("350", "34856095", "348566039")
        ),
        Country("GR", arrayOf("30")),
        Country("GL", arrayOf("299")),
        Country("GD", arrayOf("1473")),
        Country("GP", arrayOf("590")),
        Country("GU", arrayOf("1671")),
        Country("GT", arrayOf("502")),
        Country("GN", arrayOf("224")),
        Country("GW", arrayOf("245")),
        Country("GY", arrayOf("592")),
        Country("HT", arrayOf("509")),
        Country(
            "VA",
            arrayOf(
                "379",
                "39066980",
                "39066981",
                "39066982",
                "39066983",
                "39066984",
                "39066985",
                "39066986",
                "39066987",
                "39066988",
                "39066989"
            )
        ),
        Country("HN", arrayOf("504")),
        Country("HK", arrayOf("852")),
        Country("HU", arrayOf("36")),
        Country("IS", arrayOf("354")),
        Country("IN", arrayOf("91")),
        Country("ID", arrayOf("62")),
        Country("IR", arrayOf("98")),
        Country("IQ", arrayOf("964")),
        Country("IE", arrayOf("353")),
        Country("IL", arrayOf("972")),
        Country("IT", arrayOf("39")),
        Country("JM", arrayOf("1876")),
        Country("JP", arrayOf("81")),
        Country("JO", arrayOf("962")),
        Country(
            "KZ",
            arrayOf(
                "7700",
                "7701",
                "7702",
                "7705",
                "7707",
                "7710",
                "7711",
                "7712",
                "7713",
                "7714",
                "7715",
                "7716",
                "7717",
                "7718",
                "7721",
                "7722",
                "7723",
                "7724",
                "7725",
                "7726",
                "7727",
                "7728",
                "7729",
                "7730",
                "7736",
                "7760",
                "7762",
                "7763",
                "7764",
                "7774",
                "7775",
                "7777"
            )
        ),
        Country("KE", arrayOf("254")),
        Country("KI", arrayOf("686")),
        Country("KP", arrayOf("850")),
        Country("KR", arrayOf("82")),
        Country("KW", arrayOf("965")),
        Country("KG", arrayOf("996")),
        Country("LA", arrayOf("856")),
        Country("LV", arrayOf("371")),
        Country("LB", arrayOf("961")),
        Country("LS", arrayOf("266")),
        Country("LR", arrayOf("231")),
        Country("LY", arrayOf("218")),
        Country("LI", arrayOf("423")),
        Country("LT", arrayOf("370")),
        Country("LU", arrayOf("352")),
        Country("MO", arrayOf("853")),
        Country("MK", arrayOf("389")),
        Country("MG", arrayOf("261")),
        Country("MW", arrayOf("265")),
        Country("MY", arrayOf("60")),
        Country("MV", arrayOf("960")),
        Country("ML", arrayOf("223")),
        Country("MT", arrayOf("356")),
        Country("MH", arrayOf("692")),
        Country("MQ", arrayOf("596")),
        Country("MR", arrayOf("222")),
        Country("MU", arrayOf("230")),
        Country("YT", arrayOf("262269", "262639")),
        Country("MX", arrayOf("52")),
        Country("FM", arrayOf("691")),
        Country("MD", arrayOf("373", "800")),
        Country("MC", arrayOf("377")),
        Country("MN", arrayOf("976")),
        Country("ME", arrayOf("382")),
        Country("MS", arrayOf("1664")),
        Country("MA", arrayOf("212")),
        Country("MZ", arrayOf("258")),
        Country("MM", arrayOf("95")),
        Country("NA", arrayOf("264")),
        Country("NR", arrayOf("674")),
        Country("NP", arrayOf("977")),
        Country("NL", arrayOf("31")),
        Country("NC", arrayOf("687")),
        Country(
            "NZ",
            arrayOf(
                "64",
                "642",
                "643",
                "644",
                "6450",
                "646",
                "647",
                "64800",
                "6483",
                "6486",
                "6487",
                "649"
            )
        ),
        Country("NI", arrayOf("505")),
        Country("NE", arrayOf("227")),
        Country("NG", arrayOf("234")),
        Country("NU", arrayOf("683")),
        Country("MP", arrayOf("1670")),
        Country("NO", arrayOf("47")),
        Country("OM", arrayOf("968")),
        Country("PK", arrayOf("92")),
        Country("PW", arrayOf("680")),
        Country(
            "PS",
            arrayOf(
                "970",
                "9721700",
                "9721800",
                "9722220",
                "9722222",
                "9722223",
                "9722224",
                "9722225",
                "9722226",
                "9722227",
                "9722228",
                "9722229",
                "9722231",
                "9722232",
                "9722234",
                "9722240",
                "9722241",
                "9722244",
                "9722247",
                "9722248",
                "9722249",
                "9722252",
                "9722254",
                "9722256",
                "9722258",
                "9722274",
                "9722276",
                "9722277",
                "9722279",
                "9722280",
                "9722281",
                "9722286",
                "9722289",
                "9722290",
                "9722295",
                "9722296",
                "9722298",
                "9724241",
                "9724242",
                "9724243",
                "9724244",
                "9724245",
                "9724246",
                "9724250",
                "9724251",
                "9724252",
                "97256",
                "97259",
                "9728205",
                "9728206",
                "9728213",
                "9728214",
                "9728245",
                "9728247",
                "9728253",
                "9728255",
                "9728256",
                "9728280",
                "9728281",
                "9728282",
                "9728283",
                "9728284",
                "9728285",
                "9728286",
                "9728287",
                "9729232",
                "9729233",
                "9729237",
                "9729238",
                "9729239",
                "9729250",
                "9729251",
                "9729252",
                "9729253",
                "9729257",
                "9729258",
                "9729259",
                "9729260",
                "9729261",
                "9729266",
                "9729267",
                "9729268",
                "9729290",
                "9729294",
                "9729299"
            )
        ),
        Country("PA", arrayOf("507")),
        Country("PG", arrayOf("675")),
        Country("PY", arrayOf("595")),
        Country("PE", arrayOf("51")),
        Country("PH", arrayOf("63")),
        Country("PL", arrayOf("48")),
        Country("PT", arrayOf("351")),
        Country("PR", arrayOf("1787", "1939")),
        Country("QA", arrayOf("974")),
        Country("RE", arrayOf("262")),
        Country("RO", arrayOf("40")),
        Country(
            "RU",
            arrayOf("7", "995122", "99544")
        ),
        Country("RW", arrayOf("250")),
        Country("SH", arrayOf("290")),
        Country("KN", arrayOf("1869")),
        Country("LC", arrayOf("1758")),
        Country("PM", arrayOf("508")),
        Country("VC", arrayOf("1784")),
        Country("WS", arrayOf("685")),
        Country("SM", arrayOf("378")),
        Country("ST", arrayOf("239")),
        Country("SA", arrayOf("966")),
        Country("SN", arrayOf("221")),
        Country(
            "RS",
            arrayOf("381", "37744", "37747")
        ),
        Country("SC", arrayOf("248")),
        Country("SL", arrayOf("232")),
        Country("SG", arrayOf("65")),
        Country("SK", arrayOf("421")),
        Country("SI", arrayOf("386")),
        Country("SB", arrayOf("677")),
        Country(
            "SO",
            arrayOf(
                "252",
                "2522",
                "252200",
                "252201",
                "252203",
                "252204",
                "252208",
                "252210",
                "252211",
                "252212",
                "252213",
                "252214",
                "25227",
                "252271",
                "252272",
                "252273",
                "252274",
                "252275",
                "252276",
                "25228",
                "25229",
                "25265",
                "252731",
                "252732",
                "252741",
                "252742",
                "252751",
                "252825",
                "252828",
                "252829",
                "870"
            )
        ),
        Country("SX", arrayOf("1721")),
        Country("ZA", arrayOf("27")),
        Country("SS", arrayOf("211")),
        Country("ES", arrayOf("34")),
        Country("LK", arrayOf("94")),
        Country("SD", arrayOf("249")),
        Country("SR", arrayOf("597")),
        Country("SZ", arrayOf("268")),
        Country("SE", arrayOf("46")),
        Country("CH", arrayOf("41")),
        Country("SY", arrayOf("963")),
        Country("TW", arrayOf("866", "886")),
        Country("TJ", arrayOf("992")),
        Country("TZ", arrayOf("255")),
        Country("TH", arrayOf("66")),
        Country("TL", arrayOf("670")),
        Country("TG", arrayOf("228")),
        Country("TK", arrayOf("690")),
        Country("TO", arrayOf("676")),
        Country("TT", arrayOf("1868")),
        Country("TN", arrayOf("216")),
        Country("TR", arrayOf("90")),
        Country("TM", arrayOf("993")),
        Country("TC", arrayOf("1649")),
        Country("TV", arrayOf("688")),
        Country("UG", arrayOf("256")),
        Country("UA", arrayOf("380")),
        Country("AE", arrayOf("971")),
        Country("GB", arrayOf("44", "872")),
        Country("US", arrayOf("1", "699")),
        Country("UY", arrayOf("598")),
        Country("UZ", arrayOf("998")),
        Country("VU", arrayOf("678")),
        Country("VE", arrayOf("58")),
        Country("VN", arrayOf("84")),
        Country("VG", arrayOf("1284")),
        Country("VI", arrayOf("1340")),
        Country("WF", arrayOf("681")),
        Country("YE", arrayOf("967")),
        Country("ZM", arrayOf("260")),
        Country("ZW", arrayOf("263"))
    )
}

fun Countries.getDialCodesByIsoCode(countryCode: String?): Array<String>? {
    return countryCode?.let {
        byIsoCode[it]?.dialingCodes
    }
}
