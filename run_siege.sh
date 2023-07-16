CONCURRENCY=100
URL_FILE="urls.txt"
REQUEST_BODY_FILE="request_body.json"

while IFS= read -r url; do
  echo "Testing URL: $url"

  siege --concurrent="$CONCURRENCY" -t20S --content-type="application/json" "$url POST < $REQUEST_BODY_FILE"
done < "$URL_FILE"