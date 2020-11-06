// getEpochTime

int getEpochTime() {
  struct tm timeInfo;
  if (!getLocalTime(&timeInfo)) {
    return -1;
  }
  time_t timeSinceEpoch = mktime(&timeInfo);
  return timeSinceEpoch;
}
