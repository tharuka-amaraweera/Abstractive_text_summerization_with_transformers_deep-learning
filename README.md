# Abstractive text summarization with Transformers
Abstractive text summarization using transformers (multi-head attention mechanism).

Summarization is the task of reducing the size of the document while preserving its meaning. Abstractive Summarization includes heuristic approaches to train the system in making an attempt to understand the whole context and generate a summary based on that understanding.

**Pre-requirements**
1. Tensorflow
2. Pandas
utilities: pickle, re

**Dataset**: 'Inshorts' is a dataset service that has collections of news from various sources and publishes them as a summary.

**Components of the pipeline**

1. Utilities: Positional encoding and masking.
2. Model: Mulit head attention with encoder-decoder blocks.
3. Training loop and inference for testing.

**How to run**

The main script contains all the components including classes for multi-head attention mechanism, model, and the training loop. One can also change the hyper-parameters according to preference. Training data is already included in the */data* directory. 

  ```
  python Summarizer.py
  ```
