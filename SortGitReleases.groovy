// test a groovy script here: https://gwc-experiment.appspot.com/#vfYmTx21MC
// test a regex here for groovy: https://regex-testdrive.com/en/dotest

def reg = ~/v{0,1}([0-9]+){1}(.([0-9]+))?(.([0-9]+))?(.([0-9]+))?(-[a-zA-Z]+.([0-9]+))?/
def gettags = ("git ls-remote -t https://logicielsubscriptions:ghp_asdfsadf@github.com/LogicielServices/VtraderWebApi.git").execute()

def sorted = gettags.text.readLines().collect {
    it.split()[1].replaceAll('refs/heads/', '').replaceAll('refs/tags/', '').replaceAll("\\^\\{\\}", '')
  }
  .collect { versionStr ->
    // https://stackoverflow.com/a/65124878
    def matcher = versionStr =~ reg
    def major = null, feat = null, minor = null, patch = null, pre = null

    if (matcher) {
      major = matcher[0][1]
      feat = matcher[0][3]
      minor = matcher[0][5]
      patch = matcher[0][7]
      pre = matcher[0][9]
    }

    major = (major ? major : '0').toInteger() * 1000_000_000_000_000L
    feat = (feat ? feat : '0').toInteger() * 1000_000_000_000L
    minor = (minor ? minor : '0').toInteger() * 1000_000_000L
    patch = (patch ? patch : '0').toInteger() * 1000_000L
    pre = (pre ? pre : '0').toInteger()

    def versionNumber = major + feat + minor + patch + pre
    [ v: versionStr, v2: versionStr + ".. $major, $feat, $minor, $patch, $pre, $versionNumber", n: versionNumber ]
  }
  .sort { b, a -> a.n <=> b.n }
  .collect { obj -> obj.v }
return sorted
