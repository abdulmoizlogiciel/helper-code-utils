function calcDuration(timeStr = '') {
  const startAndEnd = timeStr.split(" ");
  const start = startAndEnd[0];
  const end = startAndEnd[1];
  const startDate = new Date("1970-01-01T" + start).getTime();
  const endDate = new Date("1970-01-01T" + end).getTime();
  const remainingMilliSeconds = endDate - startDate;
  const totalSeconds = (remainingMilliSeconds / 1000);
  const totalMinutes = Math.floor(totalSeconds / 60);
  const remainingSeconds = totalSeconds % 60;
  return `Total seconds: ${totalSeconds}. ${totalMinutes}m ${remainingSeconds}s`;
}
