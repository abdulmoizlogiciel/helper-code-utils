function computeVerstionStringToNumber(version = "") {
  if (/^\d+(\.\d+){1,3}$/.test(version)) {
    return 0;
  }
  const vNumbers = version.split(".");
  const major = parseInt(vNumbers[0]);
  const minor = parseInt(vNumbers[1]?.replace('.', '') || "0");
  const patch = parseInt(vNumbers[2]?.replace('.', '') || "0");
  const hotfix = parseInt(vNumbers[3]?.replace('.', '') || "0");
  console.log(major, minor, patch, hotfix);
  return (major * 10000) + (minor * 1000) + (patch * 100) + hotfix;
}
