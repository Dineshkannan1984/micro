#run below commands one by one in command prompt
kubectl create namespace microcks
kubectl create -f "https://strimzi.io/install/latest?namespace=microcks" -n microcks
kubectl apply -f https://strimzi.io/examples/latest/kafka/kafka-persistent-single.yaml -n kafka