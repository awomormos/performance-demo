# Performance Demo for Cloud Stammtisch

## Usage

First, a local installation of kind has to be established. Of course the kubernetes resources are compatible with a regular kubernetes installation and minicube as well, but this tutorial is focusing on [kind](https://kind.sigs.k8s.io/).

This cluster is set up using the script ```kind-with-registry.sh``` in the top level folder of this project.

After doing so we have to enable the metrics-server in order to be able to collect metrics from pods to evaluate them for scaling purposes.

This can be achieved by using the following two commands: 

```shell
kubectl apply -f src/main/kubernetes/metrics-server.yaml
kubectl patch -n kube-system deployment metrics-server --type=json -p '[{"op":"add","path":"/spec/template/spec/containers/0/args/-","value":"--kubelet-insecure-tls"}]'
```

After that, a metrics server in your cluster is available, and we are ready to dump our resources into the cluster.

We do this in the namespace __performance-demo__.

```shell
kubectl apply -f service.yaml
kubectl apply -f autoscaler.yaml
```

After that we can recognize, that the performance-demo pod is started.

If we now generate a little load, we should see, how the autoscaler is spawning new instances of the performance-demo pod. This can be achieved with the `generateLoad.sh`-script.