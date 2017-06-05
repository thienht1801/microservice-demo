./ecagent_linux_sys -mod gateway -lpt ${PORT} -zon ea301108-c9e5-44f5-98cd-478a9d2e4cee -sst https://ea301108-c9e5-44f5-98cd-478a9d2e4cee.run.aws-usw02-pr.ice.predix.io -tkn YWRtaW46OUVEMTkxdEBJZWEzMDExMDgtYzllNS00NGY1LTk4Y2QtNDc4YTlkMmU0Y2Vl -dbg &

sleep 5;

./ecagent_linux_sys -mod client -aid qsQVL5 -hst ws://localhost:${PORT}/agent -cid predix_client -csc IM_SO_SECRET -oa2 https://8acdb88d-40df-4c41-a89b-1398ee1916a4.predix-uaa.run.aws-usw02-pr.ice.predix.io/oauth/token -dur 30000 -dbg -tid 7ijUyC