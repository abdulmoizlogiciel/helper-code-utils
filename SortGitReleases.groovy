// test a groovy script here: https://gwc-experiment.appspot.com/#vfYmTx21MC
// test a regex here for groovy: https://regex-testdrive.com/en/dotest

def reg = ~/v{0,1}([0-9]+){1}(.([0-9]+))?(.([0-9]+))?(.([0-9]+))?(-[a-zA-Z]+.([0-9]+))?/
def gettags = ("git ls-remote -t -h https://logicielsubscriptions:ghp_asdfasdfasdf@github.com/LogicielServices/OMSApi.git").execute()

def sorted = gettags.text.readLines().collect {
    it.split()[1].replaceAll('refs/heads/', '').replaceAll('refs/tags/', '').replaceAll("\\^\\{\\}", '')
  }
  .collect { versionStr ->
    // https://stackoverflow.com/a/65124878
    def matcher = versionStr =~ reg

    def major = (matcher ? matcher[0][1] ?: '0': '0').toInteger() * 1000_000_000_000_000L
    def feat = (matcher ? matcher[0][3] ?: '0': '0').toInteger() * 1000_000_000_000L
    def minor = (matcher ? matcher[0][5] ?: '0': '0').toInteger() * 1000_000_000L
    def patch = (matcher ? matcher[0][7] ?: '0': '0').toInteger() * 1000_000L
    def pre = (matcher ? matcher[0][9] ?: '0': '0').toInteger()

    def versionNumber = major + feat + minor + patch + pre
    [ v: versionStr, v2: versionStr + ".. $major, $feat, $minor, $patch, $pre, $versionNumber", n: versionNumber ]
  }
  .sort { b, a -> a.n <=> b.n } 
  .collect { obj -> obj.v }
return sorted
